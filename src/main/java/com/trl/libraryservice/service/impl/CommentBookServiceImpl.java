package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.CommentBookRepository;
import com.trl.libraryservice.repository.SubCommentCommentRepository;
import com.trl.libraryservice.repository.entity.CommentBookEntity;
import com.trl.libraryservice.service.CommentBookService;

import static com.trl.libraryservice.service.converter.CommentBookConverter.mapEntityToDTO;
import static com.trl.libraryservice.service.converter.CommentBookConverter.mapPageEntityToPageDTO;

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
 * This class is designed to implementation methods of {@code CommentBookService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class CommentBookServiceImpl implements CommentBookService {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST = "Comments with this bookId = %s not exist.";
    private static final String EXCEPTION_MESSAGE_COMMENT_BY_COMMENT_ID_NOT_EXIST = "Comment with this commentId = %s not exist.";

    private final CommentBookRepository commentBookRepository;
    private final BookRepository bookRepository;
    private final SubCommentCommentRepository subCommentRepository;
    private final WebClient.Builder webClientBuilder;

    public CommentBookServiceImpl(CommentBookRepository commentBookRepository, BookRepository bookRepository, SubCommentCommentRepository subCommentRepository, WebClient.Builder webClientBuilder) {
        this.commentBookRepository = commentBookRepository;
        this.bookRepository = bookRepository;
        this.subCommentRepository = subCommentRepository;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Add the {@literal CommentBookDTO} by this {@code bookId}.
     * If the {@code commentBook} have field subComments, this method not be added subComments.
     *
     * @param bookId      must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentBook must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null}
     *                                  or if {@code bookId} is equal or less zero.
     *                                  And if {@code commentBook} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code commentBook} is equals {@literal null}, or less zero.
     * @throws UserNotExistException    in case if user not exist by {@code commentBook.userId}.
     * @throws BookNotExistException    in case if book not exist by {@code commentBook.bookId}.
     */
    @Override
    public void add(Long bookId, CommentBookDTO commentBook) {

        if ((bookId == null) || (bookId <= 0) || (commentBook == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> bookId = " + bookId + " ---> commentBook = " + commentBook);
        checkParametersCommentBook(commentBook);

        LOG.debug("************ add() ---> bookId = " + bookId);
        checkExistsBookById(bookId);

        LOG.debug("************ add() ---> userId = " + commentBook.getUserId());
        checkExistsUserById(commentBook.getUserId());

        commentBookRepository.add(commentBook.getUserId(), commentBook.getText(), commentBook.getDate(), bookId);

        LOG.debug("************ add() ---> commentBook is added.");
    }

    /**
     * Retrieves the {@literal CommentBookDTO} by this {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal CommentBookDTO} with the given {@code commentId}.
     * @throws IllegalArgumentException In case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal CommentBookDTO} not exist with this {@code commentId}.
     */
    @Override
    public CommentBookDTO getById(Long commentId) {
        CommentBookDTO commentBookResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getById() ---> commentId = " + commentId);

        Optional<CommentBookEntity> commentByCommentId = commentBookRepository.findById(commentId);
        LOG.debug("************ getById() ---> " +
                "commentBookFromRepositoryByCommentId = " + commentByCommentId);

        if (commentByCommentId.isEmpty()) {
            LOG.debug("************ getById() ---> " +
                    format(EXCEPTION_MESSAGE_COMMENT_BY_COMMENT_ID_NOT_EXIST, commentId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_COMMENT_BY_COMMENT_ID_NOT_EXIST, commentId));
        }

        commentBookResult = mapEntityToDTO(commentByCommentId.get());

        LOG.debug("************ getById() ---> commentBookResult = " + commentBookResult);

        return commentBookResult;
    }

    /**
     * Retrieve Page of {@literal CommentBookDTOs} by this {@code bookId}.
     *
     * @param bookId must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<CommentBookDTO>} with the given {@code bookId}.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null} or if {@code bookId} is equal or less zero.
     * @throws BookNotExistException in case if book with this {@literal bookId} not exist.
     * @throws DataNotFoundException    in case if {@literal List<CommentBookDTO>} not exist with this {@code bookId}.
     */
    @Override
    public Page<CommentBookDTO> getAllByBookId(Long bookId, Integer startPage, Integer pageSize) {
        Page<CommentBookDTO> pageOfCommentBookResult = null;

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ getAllByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getAllByBookId() ---> bookId = " + bookId + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsBookById(bookId);

        Page<CommentBookEntity> pageOfCommentsByBookId = commentBookRepository.findByBookId_RetrievePage(bookId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getAllByBookId() ---> pageOfCommentsFromRepositoryByBookId = " + pageOfCommentsByBookId);

        if (pageOfCommentsByBookId.isEmpty()) {
            LOG.debug("************ getAllByBookId() ---> " + format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
        }

        pageOfCommentBookResult = mapPageEntityToPageDTO(pageOfCommentsByBookId);
        LOG.debug("************ getAllByBookId() ---> pageOfCommentBooResult = " + pageOfCommentBookResult);

        return pageOfCommentBookResult;
    }

    @Override
    public CommentBookDTO updateById(Long commentId, CommentBookDTO commentBook) {
        // TODO: Terminar este methodo
        return null;
    }

    /**
     * Delete the {@literal CommentBookDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code commentId} is {@literal null}, and if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException If comment not exist with the {@code commentId}.
     */
    @Override
    public void deleteById(Long commentId) {

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ deleteById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteById() --->  commentId = " + commentId);

        checkExistsCommentByCommentId(commentId);

        subCommentRepository.deleteAllByCommentId(commentId);
        commentBookRepository.deleteById(commentId);

        LOG.debug("************ deleteById() ---> " + "Deleted comment by commentId = " + commentId);
    }

    /**
     * Delete all comments by {@code bookId}.
     *
     * @param bookId must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code bookId} is {@literal null}, and if {@code bookId} is equal or less zero.
     * @throws BookNotExistException    If book not exist with the {@code bookId}.
     * @throws CommentNotExistException If comments not exist with the {@code bookId}.
     */
    @Override
    public void deleteAllByBookId(Long bookId) {

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ deleteAllByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllByBookId() ---> bookId = " + bookId);

        checkExistsBookById(bookId);
        checkExistsCommentsByBookId(bookId);

        for (CommentBookEntity entity : commentBookRepository.findByBookId(bookId)) {
            subCommentRepository.deleteAllByCommentId(entity.getId());
        }

        commentBookRepository.deleteAllByBookId(bookId);

        LOG.debug("************ deleteAllByBookId() ---> " + "Deleted all comments by bookId = " + bookId);
    }

    private void checkParametersCommentBook(CommentBookDTO commentBook) {
        String message = "Field '%s', check the field that it has the 'commentBook' parameter.";

        if (commentBook.getUserId() == null) {
            LOG.debug("************ add() ---> " + format(message, "'userId' not be equals to null"));
            throw new IllegalValueException(format(message, "'userId' not be equals to null"));
        } else if (commentBook.getUserId() <= 0) {
            LOG.debug("************ add() ---> " + format(message, "'userId' must be greater that zero"));
            throw new IllegalValueException(format(message, "'userId' must be greater that zero"));
        }

        if (commentBook.getText() == null) {
            LOG.debug("************ add() ---> " + format(message, "'text' not be equals to null"));
            throw new IllegalValueException(format(message, "'text' not be equals to null"));
        } else if ((deleteWhitespace(commentBook.getText()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'text' is empty"));
            throw new IllegalValueException(format(message, "'text' is empty"));
        }

        if (commentBook.getDate() == null) {
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

    private void checkExistsBookById(Long bookId) {
        if (bookRepository.findById(bookId).isEmpty()) {
            LOG.debug("************ checkExistsBookById() ---> " + "Book with this id = " + bookId + " not exist.");
            throw new BookNotExistException("Book with this id = " + bookId + " not exist.");
        }
    }

    private void checkExistsCommentByCommentId(Long commentId) {
        if (commentBookRepository.findById(commentId).isEmpty()) {
            LOG.debug("************ checkExistsCommentByCommentId() ---> " + "Comment with this commentId = " + commentId + " not exist.");
            throw new CommentNotExistException("Comment with this commentId = " + commentId + " not exist.");
        }
    }

    private void checkExistsCommentsByBookId(Long bookId) {
        if (commentBookRepository.findByBookId(bookId).isEmpty()) {
            LOG.debug("************ checkExistsCommentsByBookId() ---> Comments with this bookId = " + bookId + " not exist.");
            throw new CommentNotExistException("Comments with this bookId = " + bookId + " not exist.");
        }
    }
}
