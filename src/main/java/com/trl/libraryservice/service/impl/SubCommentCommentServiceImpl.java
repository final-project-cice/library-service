package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.CommentBookRepository;
import com.trl.libraryservice.repository.SubCommentCommentRepository;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;
import com.trl.libraryservice.service.SubCommentCommentService;
import com.trl.libraryservice.utils.UserUtils;

import static com.trl.libraryservice.service.converter.SubCommentCommentConverter.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
import java.util.Optional;

/**
 * This class is designed to implement methods of service {@literal SubCommentCommentService}.
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
    private final WebClient webClient;

    public SubCommentCommentServiceImpl(SubCommentCommentRepository subCommentRepository, CommentBookRepository commentBookRepository, WebClient webClient) {
        this.subCommentRepository = subCommentRepository;
        this.commentBookRepository = commentBookRepository;
        this.webClient = webClient;
    }

    /**
     * Add the {@literal SubCommentCommentDTO} by this {@code commentId}.
     *
     * @param commentId  must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param subComment must not be equal to {@literal null}.
     * @return {@literal SubCommentCommentDTO} this subComment to be saved.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null}
     *                                  or if {@code commentId} is equal or less zero.
     *                                  And if {@code subComment} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code subComment} is equals {@literal null},
     *                                  or less zero, or is empty.
     * @throws UserNotExistException    in case if user not exist by {@code subComment.userId}.
     * @throws CommentNotExistException in case if comment not exist by {@code commentId}.
     */
    @Override
    public SubCommentCommentDTO add(Long commentId, SubCommentCommentDTO subComment) {
        SubCommentCommentDTO subCommentResult = null;

        if ((commentId == null) || (commentId <= 0) || (subComment == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> commentId = " + commentId + " ---> sbComment = " + subComment);
        checkParametersSubCommentBook(subComment);

        LOG.debug("************ add() ---> commentId = " + commentId);
        checkExistsCommentById(commentId);

        LOG.debug("************ add() ---> userId = " + subComment.getUserId());
        UserUtils.checkExistsUserById(subComment.getUserId(), webClient);

        // TODO: Find information. How can these two lines of code be done better.
        Long generatedId = subCommentRepository.count() + 1;
        subCommentRepository.add(generatedId, subComment.getDate(), subComment.getText(), subComment.getUserId(), commentId);

        SubCommentCommentEntity savedSubCommentFromRepository = subCommentRepository.findById(generatedId).get();

        subCommentResult = mapEntityToDTO(savedSubCommentFromRepository);

        LOG.debug("************ add() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
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
    public SubCommentCommentDTO getBySubCommentId(Long subCommentId) {
        SubCommentCommentDTO subCommentResult = null;

        if ((subCommentId == null) || (subCommentId <= 0)) {
            LOG.debug("************ getBySubCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getBySubCommentId() ---> subCommentId = " + subCommentId);

        Optional<SubCommentCommentEntity> subCommentBySubCommentId = subCommentRepository.findById(subCommentId);
        LOG.debug("************ getBySubCommentId() ---> " +
                "subCommentFromRepositoryBySubCommentId = " + subCommentBySubCommentId);

        if (!subCommentBySubCommentId.isPresent()) {
            LOG.debug("************ getBySubCommentId() ---> " +
                    format(EXCEPTION_MESSAGE_SUB_COMMENT_BY_SUB_COMMENT_ID_NOT_EXIST, subCommentId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_SUB_COMMENT_BY_SUB_COMMENT_ID_NOT_EXIST, subCommentId));
        }

        subCommentResult = mapEntityToDTO(subCommentBySubCommentId.get());

        LOG.debug("************ getBySubCommentId() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    /**
     * Retrieve Page of {@literal SubCommentCommentDTOs} by this {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<SubCommentCommentDTO>} with the given {@code commentId}.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException in case if comment with this {@literal commentId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<SubCommentCommentDTO>} not exist with this {@code commentId}.
     */
    @Override
    public Page<SubCommentCommentDTO> getPageOfSubCommentsByCommentId(Long commentId, Integer startPage, Integer pageSize) {
        Page<SubCommentCommentDTO> subCommentPageResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getPageOfSubCommentsByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfSubCommentsByCommentId() ---> commentId = " + commentId
                + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsCommentById(commentId);

        Page<SubCommentCommentEntity> subCommentsByCommentId = subCommentRepository.getPageOfSubCommentsByCommentId(commentId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfSubCommentsByCommentId() ---> subCommentsFromRepositoryByCommentId = " + subCommentsByCommentId);

        if (subCommentsByCommentId.isEmpty()) {
            LOG.debug("************ getPageOfSubCommentsByCommentId() ---> " + format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
        }

        subCommentPageResult = mapPageEntityToPageDTO(subCommentsByCommentId);
        LOG.debug("************ getPageOfSubCommentsByCommentId() ---> subCommentsPageResult = " + subCommentPageResult);

        return subCommentPageResult;
    }

    /**
     * Retrieve Page of sorted {@literal SubCommentCommentDTOs} by this {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted SubCommentCommentDTOs will be. Must not be {@literal null}.
     * @return the {@literal Page<SubCommentCommentDTO>} with the given {@code commentId}.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException in case if comment with this {@literal commentId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<SubCommentCommentDTO>} not exist with this {@code commentId}.
     */
    @Override
    public Page<SubCommentCommentDTO> getPageOfSortedSubCommentsByCommentId(Long commentId, Integer startPage, Integer pageSize, String sortOrder) {
        Page<SubCommentCommentDTO> subCommentPageResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getPageOfSortedSubCommentsByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfSortedSubCommentsByCommentId() ---> commentId = " + commentId
                + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsCommentById(commentId);

        Page<SubCommentCommentEntity> subCommentsByCommentId = subCommentRepository.getPageOfSubCommentsByCommentId(commentId, PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedSubCommentsByCommentId() ---> subCommentsFromRepositoryByCommentId = " + subCommentsByCommentId);

        if (subCommentsByCommentId.isEmpty()) {
            LOG.debug("************ getPageOfSortedSubCommentsByCommentId() ---> " + format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_SUB_COMMENTS_BY_COMMENT_ID_NOT_EXIST, commentId));
        }

        subCommentPageResult = mapPageEntityToPageDTO(subCommentsByCommentId);
        LOG.debug("************ getPageOfSortedSubCommentsByCommentId() ---> subCommentsPageResult = " + subCommentPageResult);

        return subCommentPageResult;
    }

    /**
     * Update the {@literal SubCommentCommentDTO} by this {@code subCommentId}.
     *
     * @param subCommentId must not be equal to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @param subComment   must not be equal to {@literal null}.
     * @return the updated {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     * @throws IllegalArgumentException in case the given {@code subCommentId} is {@literal null}
     *                                  or if {@code subCommentId} is equal or less zero.
     *                                  And if {@code subComment} is equals to {@literal null}.
     * @throws DataNotFoundException    in case if {@literal SubCommentCommentDTO} not exist by {@code commentId}.
     * @throws TheSameValueException    in case if source value field is equals to current value field.
     */
    @Override
    public SubCommentCommentDTO updateBySubCommentId(Long subCommentId, SubCommentCommentDTO subComment) {
        SubCommentCommentDTO subCommentResult = null;

        if ((subCommentId == null) || (subCommentId <= 0) || (subComment == null)) {
            LOG.debug("************ updateBySubCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ updateBySubCommentId() ---> subCommentId = " + subCommentId + " ---> subComment = " + subComment);

        SubCommentCommentEntity subCommentToBeUpdated = checkExistsSubCommentBySubCommentId(subCommentId);

        // TODO: Finish this method.
        subCommentResult = mapEntityToDTO(subCommentToBeUpdated);

        LOG.debug("************ updateBySubCommentId() ---> " + "Updated subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    /**
     * Delete the {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     *
     * @param subCommentId must not be equal to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @return {@literal SubCommentCommentDTO} this subComment will be deleted.
     * @throws IllegalArgumentException    In case if the given {@code subCommentId} is {@literal null}, and if {@code subCommentId} is equal or less zero.
     * @throws SubCommentNotExistException If subComment not exist with the {@code subCommentId}.
     */
    @Override
    public SubCommentCommentDTO deleteBySubCommentId(Long subCommentId) {
        SubCommentCommentDTO subCommentResult = null;

        if ((subCommentId == null) || (subCommentId <= 0)) {
            LOG.debug("************ deleteBySubCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteBySubCommentId() --->  subCommentId = " + subCommentId);

        SubCommentCommentEntity subCommentToBeDeleted = checkExistsSubCommentBySubCommentId(subCommentId);

        subCommentRepository.deleteById(subCommentId);

        subCommentResult = mapEntityToDTO(subCommentToBeDeleted);

        LOG.debug("************ deleteBySubCommentId() ---> " + "Deleted subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    /**
     * Delete all subComments by {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @return {@literal List<SubCommentCommentDTO>} this subComments will be deleted.
     * @throws IllegalArgumentException    In case if the given {@code commentId} is {@literal null}, and if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException    If comment not exist with the {@code commentId}.
     * @throws SubCommentNotExistException If subComments not exist with the {@code commentId}.
     */
    @Override
    public List<SubCommentCommentDTO> deleteAllByCommentId(Long commentId) {
        List<SubCommentCommentDTO> subCommentsResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ deleteAllByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllByCommentId() ---> commentId = " + commentId);

        checkExistsCommentById(commentId);
        List<SubCommentCommentEntity> subCommentsToBeDeleted = checkExistsSubCommentsByCommentId(commentId);

        subCommentRepository.deleteAllByCommentId(commentId);

        subCommentsResult = mapListEntityToListDTO(subCommentsToBeDeleted);

        LOG.debug("************ deleteAllByCommentId() ---> " + "Deleted subCommentsResult = " + subCommentsResult);

        return subCommentsResult;
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

    private void checkExistsCommentById(Long commentId) {
        if (!commentBookRepository.existsById(commentId)) {
            LOG.debug("************ checkExistsCommentById() ---> " + "Comment with this id = " + commentId + " not exist.");
            throw new CommentNotExistException("Comment with this id = " + commentId + " not exist.");
        }
    }

    private SubCommentCommentEntity checkExistsSubCommentBySubCommentId(Long subCommentId) {
        Optional<SubCommentCommentEntity> subCommentFromRepository = subCommentRepository.findById(subCommentId);
        if (!subCommentFromRepository.isPresent()) {
            LOG.debug("************ checkExistsSubCommentBySubCommentId() ---> " + "SubComment with this subCommentId = " + subCommentId + " not exist.");
            throw new CommentNotExistException("SubComment with this subCommentId = " + subCommentId + " not exist.");
        }
        return subCommentFromRepository.get();
    }

    private List<SubCommentCommentEntity> checkExistsSubCommentsByCommentId(Long commentId) {
        List<SubCommentCommentEntity> subCommentsFromRepository = subCommentRepository.findByCommentId(commentId);
        if (subCommentsFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsSubCommentsByCommentId() ---> SubComments with this commentId = " + commentId + " not exist.");
            throw new CommentNotExistException("SubComments with this commentId = " + commentId + " not exist.");
        }
        return subCommentsFromRepository;
    }
}