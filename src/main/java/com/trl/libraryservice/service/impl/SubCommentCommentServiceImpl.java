package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.CommentBookRepository;
import com.trl.libraryservice.repository.SubCommentCommentRepository;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;
import com.trl.libraryservice.service.SubCommentCommentService;

import static com.trl.libraryservice.service.converter.SubCommentCommentConverter.mapEntityToDTO;
import static com.trl.libraryservice.service.converter.SubCommentCommentConverter.mapPageEntityToPageDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.Optional;

/**
 * This class is designed to support service layout for {@literal SubCommentCommentDTO}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class SubCommentCommentServiceImpl implements SubCommentCommentService {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST = "SubComments with this commentId = %s not exist.";
    private static final String EXCEPTION_MESSAGE_SUB_COMMENT_BY_SUB_COMMENT_ID_NOT_EXIST = "SubComment with this subCommentId = %s not exist.";

    private final SubCommentCommentRepository subCommentRepository;
    private final CommentBookRepository commentBookRepository;
    private final WebClient.Builder webClientBuilder;

    public SubCommentCommentServiceImpl(SubCommentCommentRepository subCommentRepository, CommentBookRepository commentBookRepository, WebClient.Builder webClientBuilder) {
        this.subCommentRepository = subCommentRepository;
        this.commentBookRepository = commentBookRepository;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Add the {@literal SubCommentCommentDTO} by this {@code commentId}.
     *
     * @param commentId  must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param subComment must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null}
     *                                  or if {@code commentId} is equal or less zero.
     *                                  And if {@code subComment} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code subComment} is equals {@literal null},
     *                                  or less zero, or is empty.
     * @throws UserNotExistException    in case if user not exist by {@code subComment.userId}.
     * @throws CommentNotExistException in case if comment not exist by {@code commentId}.
     */
    @Override
    public void add(Long commentId, SubCommentCommentDTO subComment) {

        if ((commentId == null) || (commentId <= 0) || (subComment == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> commentId = " + commentId + " ---> sbComment = " + subComment);
        checkParametersSubCommentBook(subComment);

        LOG.debug("************ add() ---> commentId = " + commentId);
        checkExistsCommentById(commentId);

        LOG.debug("************ add() ---> userId = " + subComment.getUserId());
        checkExistsUserById(subComment.getUserId());

        subCommentRepository.add(subComment.getUserId(), subComment.getText(), subComment.getDate(), commentId);

        LOG.debug("************ add() ---> subComment is added.");
    }

    /**
     * Retrieves the {@literal SubCommentCommentDTO} by this {@code subCommentId}.
     *
     * @param subCommentId must not be equal to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @return the {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     * @throws IllegalArgumentException In case the given {@code subCommentId} is {@literal null} or if {@code subCommentId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal SubCommentCommentDTO} not exist with this {@code subCommentId}.
     */
    @Override
    public SubCommentCommentDTO getById(Long subCommentId) {
        SubCommentCommentDTO subCommentResult = null;

        if ((subCommentId == null) || (subCommentId <= 0)) {
            LOG.debug("************ getById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getById() ---> subCommentId = " + subCommentId);

        Optional<SubCommentCommentEntity> subCommentBySubCommentId = subCommentRepository.findById(subCommentId);
        LOG.debug("************ getById() ---> " +
                "subCommentFromRepositoryBySubCommentId = " + subCommentBySubCommentId);

        if (subCommentBySubCommentId.isEmpty()) {
            LOG.debug("************ getByCommentId() ---> " +
                    format(EXCEPTION_MESSAGE_SUB_COMMENT_BY_SUB_COMMENT_ID_NOT_EXIST, subCommentId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_SUB_COMMENT_BY_SUB_COMMENT_ID_NOT_EXIST, subCommentId));
        }

        subCommentResult = mapEntityToDTO(subCommentBySubCommentId.get());

        LOG.debug("************ getById() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    /**
     * Retrieves Page {@literal SubCommentCommentDTOs} by this {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<SubCommentCommentDTO>} with the given {@code commentId}.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException in case if comment with this {@literal commentId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<SubCommentCommentDTO>} not exist with this {@code commentId}.
     */
    @Override
    public Page<SubCommentCommentDTO> getAllByCommentId(Long commentId, Integer startPage, Integer pageSize) {
        Page<SubCommentCommentDTO> subCommentListResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getAllByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getAllByCommentId() ---> commentId = " + commentId
                + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsCommentById(commentId);

        Page<SubCommentCommentEntity> subCommentsByCommentId = subCommentRepository.findByCommentId_RetrievePage(commentId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getAllByCommentId() ---> subCommentsFromRepositoryByCommentId = " + subCommentsByCommentId);

        if (subCommentsByCommentId.isEmpty()) {
            LOG.debug("************ getAllByCommentId() ---> " + format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
        }

        subCommentListResult = mapPageEntityToPageDTO(subCommentsByCommentId);
        LOG.debug("************ getAllByCommentId() ---> subCommentsListResult = " + subCommentListResult);

        return subCommentListResult;
    }

    /**
     * Update the {@literal SubCommentCommentDTO} by this {@code subCommentId}.
     *
     * @param subCommentId must not be equal to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @param subComment must not be equal to {@literal null}.
     * @return the updated {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     * @throws IllegalArgumentException in case the given {@code subCommentId} is {@literal null} or if {@code subCommentId} is equal or less zero.
     *                                  And if {@code subComment} is equls to {@literal null}.
     * @throws SubCommentNotExistException in case if subComment with this {@literal subCommentId} not exist.
     */
    @Override
    public SubCommentCommentDTO updateById(Long subCommentId, SubCommentCommentDTO subComment) {
        // TODO: Finish this.
        return null;
    }

    /**
     * Delete the {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     *
     * @param subCommentId must not be equal to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code subCommentId} is {@literal null}, and if {@code subCommentId} is equal or less zero.
     * @throws SubCommentNotExistException If subComment not exist with the {@code subCommentId}.
     */
    @Override
    public void deleteById(Long subCommentId) {

        if ((subCommentId == null) || (subCommentId <= 0)) {
            LOG.debug("************ deleteById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteById() --->  subCommentId = " + subCommentId);

        checkExistsSubCommentBySubCommentId(subCommentId);

        subCommentRepository.deleteById(subCommentId);

        LOG.debug("************ deleteById() ---> " + "Deleted subComment by subCommentId = " + subCommentId);
    }

    /**
     * Delete all subComments by {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code commentId} is {@literal null}, and if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException    If comment not exist with the {@code commentId}.
     * @throws SubCommentNotExistException If subComments not exist with the {@code commentId}.
     */
    @Override
    public void deleteAllByCommentId(Long commentId) {

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ deleteAllByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllByCommentId() ---> commentId = " + commentId);

        checkExistsCommentById(commentId);
        checkExistsSubCommentsByCommentId(commentId);

        subCommentRepository.deleteAllByCommentId(commentId);

        LOG.debug("************ deleteAllByCommentId() ---> " + "Deleted all subComments by commentId = " + commentId);
    }

    private void checkParametersSubCommentBook(SubCommentCommentDTO subComment) {
        String message = "Field '%s', check the field that it has the 'subComment' parameter.";

        if (subComment.getUserId() == null) {
            LOG.debug("************ add() ---> " + format(message, "'userId' not be equals to null"));
            throw new IllegalValueException(format(message, "'userId' not be equals to null"));
        } else if (subComment.getUserId() <= 0) {
            LOG.debug("************ add() ---> " + format(message, "'userId' must be greater that zero"));
            throw new IllegalValueException(format(message, "'userId' must be greater that zero"));
        }

        if (subComment.getText() == null) {
            LOG.debug("************ add() ---> " + format(message, "'text' not be equals to null"));
            throw new IllegalValueException(format(message, "'text' not be equals to null"));
        } else if ((deleteWhitespace(subComment.getText()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'text' is empty"));
            throw new IllegalValueException(format(message, "'text' is empty"));
        }

        if (subComment.getDate() == null) {
            LOG.debug("************ add() ---> " + format(message, "'date' not be equals to null"));
            throw new IllegalValueException(format(message, "'date' not be equals to null"));
        }
    }

    private void checkExistsUserById(Long userId) {
        webClientBuilder
                .build()
                .get()
                .uri("http://user-service/user/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new UserNotExistException("User by this id = " + userId + " not exist.")))
                .bodyToMono(UserDTO.class)
                .block();
    }

    private void checkExistsCommentById(Long commentId) {
        if (commentBookRepository.findById(commentId).isEmpty()) {
            LOG.debug("************ checkExistsCommentById() ---> " + "Comment with this id = " + commentId + " not exist.");
            throw new CommentNotExistException("Comment with this id = " + commentId + " not exist.");
        }
    }

    private void checkExistsSubCommentBySubCommentId(Long subCommentId) {
        if (subCommentRepository.findById(subCommentId).isEmpty()) {
            LOG.debug("************ checkExistsSubCommentBySubCommentId() ---> " + "SubComment with this subCommentId = " + subCommentId + " not exist.");
            throw new CommentNotExistException("SubComment with this subCommentId = " + subCommentId + " not exist.");
        }
    }

    private void checkExistsSubCommentsByCommentId(Long commentId) {
        if (subCommentRepository.findByCommentId(commentId).isEmpty()) {
            LOG.debug("************ checkExistsSubCommentsByCommentId() ---> SubComments with this commentId = " + commentId + " not exist.");
            throw new CommentNotExistException("SubComments with this commentId = " + commentId + " not exist.");
        }
    }
}