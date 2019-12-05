package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.CommentBookRepository;
import com.trl.libraryservice.repository.SubCommentCommentRepository;
import com.trl.libraryservice.repository.entity.CommentBookEntity;
import com.trl.libraryservice.service.CommentBookService;
import com.trl.libraryservice.utils.UserUtils;

import static com.trl.libraryservice.service.converter.CommentBookConverter.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
import java.util.Optional;

/**
 * This class is designed to implement methods of service {@literal CommentBookService}.
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
     * @return {@literal CommentBookDTO} this comment to be saved.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null}
     *                                  or if {@code bookId} is equal or less zero.
     *                                  And if {@code commentBook} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code commentBook} is equals {@literal null}, or less zero.
     * @throws UserNotExistException    in case if user not exist by {@code commentBook.userId}.
     * @throws BookNotExistException    in case if book not exist by {@code bookId}.
     */
    @Override
    public CommentBookDTO add(Long bookId, CommentBookDTO commentBook) {
        CommentBookDTO commentBookResult = null;

        if ((bookId == null) || (bookId <= 0) || (commentBook == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> bookId = " + bookId + " ---> commentBook = " + commentBook);
        checkParametersCommentBook(commentBook);

        LOG.debug("************ add() ---> bookId = " + bookId);
        checkExistsBookById(bookId);

        LOG.debug("************ add() ---> userId = " + commentBook.getUserId());
        UserUtils.checkExistsUserById(commentBook.getUserId(), webClientBuilder);

        // TODO: Find information. How can these two lines of code be done better.
        Long generatedId = commentBookRepository.count() + 1;
        commentBookRepository.add(generatedId, commentBook.getDate(), commentBook.getText(), commentBook.getUserId(), bookId);

        CommentBookEntity savedCommentFromRepository = commentBookRepository.findById(generatedId).get();

        commentBookResult = mapEntityToDTO(savedCommentFromRepository);

        LOG.debug("************ add() ---> commentBookResult = " + commentBookResult);

        return commentBookResult;
    }

    /**
     * Retrieves the {@literal CommentBookDTO} by this {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @return the {@literal CommentBookDTO} with the given {@code commentId}.
     * @throws IllegalArgumentException In case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal CommentBookDTO} not exist with this {@code commentId}.
     */
    @Override
    public CommentBookDTO getByCommentId(Long commentId) {
        CommentBookDTO commentBookResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getByCommentId(() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByCommentId(() ---> commentId = " + commentId);

        Optional<CommentBookEntity> commentByCommentId = commentBookRepository.findById(commentId);
        LOG.debug("************ getByCommentId(() ---> " +
                "commentBookFromRepositoryByCommentId = " + commentByCommentId);

        if (commentByCommentId.isEmpty()) {
            LOG.debug("************ getByCommentId(() ---> " +
                    format(EXCEPTION_MESSAGE_COMMENT_BY_COMMENT_ID_NOT_EXIST, commentId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_COMMENT_BY_COMMENT_ID_NOT_EXIST, commentId));
        }

        commentBookResult = mapEntityToDTO(commentByCommentId.get());

        LOG.debug("************ getByCommentId(() ---> commentBookResult = " + commentBookResult);

        return commentBookResult;
    }

    /**
     * Retrieve page of {@literal CommentBookDTOs} by this {@code bookId}.
     *
     * @param bookId    must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<CommentBookDTO>} with the given {@code bookId}.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null} or if {@code bookId} is equal or less zero.
     * @throws BookNotExistException    in case if book with this {@literal bookId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<CommentBookDTO>} not exist with this {@code bookId}.
     */
    @Override
    public Page<CommentBookDTO> getPageOfCommentsByBookId(Long bookId, Integer startPage, Integer pageSize) {
        Page<CommentBookDTO> pageOfCommentBookResult = null;

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ getPageOfCommentsByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfCommentsByBookId() ---> bookId = " + bookId + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsBookById(bookId);

        Page<CommentBookEntity> pageOfCommentsByBookId = commentBookRepository.getPageOfCommentsByBookId(bookId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfCommentsByBookId() ---> pageOfCommentsFromRepositoryByBookId = " + pageOfCommentsByBookId);

        if (pageOfCommentsByBookId.isEmpty()) {
            LOG.debug("************ getPageOfCommentsByBookId() ---> " + format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
        }

        pageOfCommentBookResult = mapPageEntityToPageDTO(pageOfCommentsByBookId);
        LOG.debug("************ getPageOfCommentsByBookId() ---> pageOfCommentBooResult = " + pageOfCommentBookResult);

        return pageOfCommentBookResult;
    }

    /**
     * Retrieve page of sorted {@literal CommentBookDTOs} by this {@code bookId}.
     *
     * @param bookId    must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted CommentBookDTOs will be. Must not be {@literal null}.
     * @return the {@literal Page<CommentBookDTO>} with the given {@code bookId}.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null} or if {@code bookId} is equal or less zero.
     * @throws BookNotExistException    in case if book with this {@literal bookId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<CommentBookDTO>} not exist with this {@code bookId}.
     */
    @Transactional
    @Override
    public Page<CommentBookDTO> getPageOfSortedCommentsByBookId(Long bookId, Integer startPage, Integer pageSize, String sortOrder) {
        Page<CommentBookDTO> pageOfCommentBookResult = null;

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ getPageOfSortedCommentsByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfSortedCommentsByBookId() ---> bookId = " + bookId + " ---> startPage = " + startPage
                + " ---> pageSize = " + pageSize + " ---> sortOrder = " + sortOrder);

        checkExistsBookById(bookId);

        Page<CommentBookEntity> pageOfCommentsByBookId = commentBookRepository.getPageOfCommentsByBookId(bookId, PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedCommentsByBookId() ---> pageOfCommentsFromRepositoryByBookId = " + pageOfCommentsByBookId);

        if (pageOfCommentsByBookId.isEmpty()) {
            LOG.debug("************ getPageOfSortedCommentsByBookId() ---> " + format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
        }

        pageOfCommentBookResult = mapPageEntityToPageDTO(pageOfCommentsByBookId);
        LOG.debug("************ getPageOfSortedCommentsByBookId() ---> pageOfCommentBooResult = " + pageOfCommentBookResult);

        return pageOfCommentBookResult;
    }

    /**
     * Update the {@literal CommentBookDTO} by this {@code commentId}.
     *
     * @param commentId   must not be {@literal null}, and {@code id} must be greater than zero.
     * @param commentBook must not be {@literal null}.
     * @return the {@literal CommentBookDTO} this comment to be updated.
     * @throws IllegalArgumentException in case the given {@code commentId} is {@literal null}
     *                                  or if {@code commentId} is equal or less zero.
     *                                  And if {@code commentBook} is equals to {@literal null}.
     * @throws DataNotFoundException    in case if {@literal CommentBookDTO} not exist by {@code commentId}.
     * @throws TheSameValueException    in case if source value field is equals to current value field.
     */
    @Override
    public CommentBookDTO updateByCommentId(Long commentId, CommentBookDTO commentBook) {
        CommentBookDTO commentResult = null;

        if ((commentId == null) || (commentId <= 0) || (commentBook == null)) {
            LOG.debug("************ updateByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ updateByCommentId() ---> commentId = " + commentId + " ---> commentBook = " + commentBook);

        CommentBookEntity commentToBeUpdated = checkExistsCommentByCommentId(commentId);

        // TODO: Finish this method.
        commentResult = mapEntityToDTO(commentToBeUpdated);

        LOG.debug("************ updateByCommentId() ---> " + "Updated commentResult = " + commentResult);

        return commentResult;
    }

    /**
     * Delete the {@literal CommentBookDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @return {@literal CommentBookDTO} this comment to be deleted.
     * @throws IllegalArgumentException In case if the given {@code commentId} is {@literal null}, and if {@code commentId} is equal or less zero.
     * @throws CommentNotExistException If comment not exist with the {@code commentId}.
     */
    @Override
    public CommentBookDTO deleteByCommentId(Long commentId) {
        CommentBookDTO commentResult = null;

        if ((commentId == null) || (commentId <= 0)) {
            LOG.debug("************ deleteByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteByCommentId() --->  commentId = " + commentId);

        CommentBookEntity commentToBeDeleted = checkExistsCommentByCommentId(commentId);

        subCommentRepository.deleteAllByCommentId(commentId);
        commentBookRepository.deleteById(commentId);

        commentResult = mapEntityToDTO(commentToBeDeleted);

        LOG.debug("************ deleteByCommentId() ---> " + "Deleted comment = " + commentResult);

        return commentResult;
    }

    /**
     * Delete all comments by {@code bookId}.
     *
     * @param bookId must not be equal to {@literal null}, and {@code bookId} must be greater than zero.
     * @return {@literal List<CommentBookDTO>} this list of comments to be deleted.
     * @throws IllegalArgumentException In case if the given {@code bookId} is {@literal null}, and if {@code bookId} is equal or less zero.
     * @throws BookNotExistException    If book not exist with the {@code bookId}.
     * @throws CommentNotExistException If comments not exist with the {@code bookId}.
     */
    @Override
    public List<CommentBookDTO> deleteAllCommentsByBookId(Long bookId) {
        List<CommentBookDTO> commentsResult = null;

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ deleteAllCommentsByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllCommentsByBookId() ---> bookId = " + bookId);

        checkExistsBookById(bookId);

        List<CommentBookEntity> commentsToBeDeleted = checkExistsCommentsByBookId(bookId);

        for (CommentBookEntity entity : commentsToBeDeleted) {
            subCommentRepository.deleteAllByCommentId(entity.getId());
        }

        commentBookRepository.deleteAllByBookId(bookId);

        commentsResult = mapListEntityToListDTO(commentsToBeDeleted);

        LOG.debug("************ deleteAllCommentsByBookId() ---> " + "Deleted comments = " + commentsToBeDeleted);

        return commentsResult;
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

    private void checkExistsBookById(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            LOG.debug("************ checkExistsBookById() ---> " + "Book with this id = " + bookId + " not exist.");
            throw new BookNotExistException("Book with this id = " + bookId + " not exist.");
        }
    }

    private CommentBookEntity checkExistsCommentByCommentId(Long commentId) {

        Optional<CommentBookEntity> commentFromRepository = commentBookRepository.findById(commentId);

        if (commentFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsCommentByCommentId() ---> " + "Comment with this commentId = " + commentId + " not exist.");
            throw new CommentNotExistException("Comment with this commentId = " + commentId + " not exist.");
        }

        return commentFromRepository.get();
    }

    private List<CommentBookEntity> checkExistsCommentsByBookId(Long bookId) {

        List<CommentBookEntity> commentsFromRepository = commentBookRepository.findByBookId(bookId);

        if (commentsFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsCommentsByBookId() ---> Comments with this bookId = " + bookId + " not exist.");
            throw new CommentNotExistException("Comments with this bookId = " + bookId + " not exist.");
        }

        return commentsFromRepository;
    }
}
