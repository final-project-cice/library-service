package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.CommentBookRepository;
import com.trl.libraryservice.repository.entity.CommentBookEntity;
import com.trl.libraryservice.service.CommentBookService;

import static com.trl.libraryservice.service.converter.CommentBookConverter.mapEntityToDTO;
import static com.trl.libraryservice.service.converter.CommentBookConverter.mapListEntityToListDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
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
    private static final String EXCEPTION_MESSAGE_COMMENT_BY_BOOK_ID_AND_COMMENT_ID_NOT_EXIST =
            "Comment with this bookId = %s and commentId = %s not exist.";


    private final CommentBookRepository commentBookRepository;
    private final BookRepository bookRepository;
    private final WebClient.Builder webClientBuilder;

    public CommentBookServiceImpl(CommentBookRepository commentBookRepository, BookRepository bookRepository, WebClient.Builder webClientBuilder) {
        this.commentBookRepository = commentBookRepository;
        this.bookRepository = bookRepository;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Add the {@literal CommentBookDTO} by this {@code bookId}.
     * If the {@code commentBook} have field subComments, this method not be added subComments.
     *
     * @param bookId      must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentBook must not be {@literal null}.
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

        LOG.debug("************ add() ---> userId = " + commentBook.getUserId());
        checkExistsUserById(commentBook.getUserId());

        LOG.debug("************ add() ---> bookId = " + bookId);
        checkExistsBookById(bookId);

        commentBookRepository.add(commentBook.getDate(), commentBook.getText(), commentBook.getUserId(), bookId);

        LOG.debug("************ add() ---> commentBook is added.");
    }

    /**
     * Retrieves the {@literal List<CommentBookDTO>} by this {@code bookId}.
     *
     * @param bookId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal List<CommentBookDTO>} with the given {@code bookId}.
     * @throws IllegalArgumentException in case the given {@code bookId} is {@literal null}
     *                                  or if {@code bookId} is equal or less zero.
     * @throws DataNotFoundException    in case if {@literal List<CommentBookDTO>} not exist with this {@code bookId}.
     */
    @Override
    public List<CommentBookDTO> getByBookId(Long bookId) {
        List<CommentBookDTO> commentBookListResult = null;

        if ((bookId == null) || (bookId <= 0)) {
            LOG.debug("************ getByBookId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByBookId() ---> bookId = " + bookId);

        List<CommentBookEntity> commentsByBookId = commentBookRepository.findByBookId(bookId);
        LOG.debug("************ getByBookId() ---> commentBookFromRepositoryByBookId = " + commentsByBookId);

        if (commentsByBookId.isEmpty()) {
            LOG.debug("************ getByBookId() ---> " + format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_COMMENTS_BY_BOOK_ID_NOT_EXIST, bookId));
        }

        commentBookListResult = mapListEntityToListDTO(commentsByBookId);
        LOG.debug("************ getByBookId() ---> commentBookListResult = " + commentBookListResult);

        return commentBookListResult;
    }

    /**
     * Retrieves the {@literal CommentBookDTO} by this {@code bookId} and {@code commentId}.
     *
     * @param bookId    must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal CommentBookDTO} with the given {@code bookId} and {@code commentId}.
     * @throws IllegalArgumentException In case the given {@code bookId} is {@literal null} or if {@code bookId} is equal or less zero.
     *                                  And in case the given {@code commentId} is {@literal null} or if {@code commentId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal CommentBookDTO} not exist with this {@code bookId} and {@code commentId}.
     */
    @Override
    public CommentBookDTO getByBookIdAndByCommentId(Long bookId, Long commentId) {
        CommentBookDTO commentBookResult = null;

        if ((bookId == null) || (bookId <= 0)
                || (commentId == null) || (commentId <= 0)) {
            LOG.debug("************ getByBookIdAndByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByBookIdAndByCommentId() ---> bookId = " + bookId + " --> commentId = " + commentId);

        Optional<CommentBookEntity> commentByBookIdAndCommentId = commentBookRepository.findByBookIdAndCommentId(bookId, commentId);
        LOG.debug("************ getByBookIdAndByCommentId() ---> " +
                "commentBookFromRepositoryByBookIdAndCommentId = " + commentByBookIdAndCommentId);


        if (commentByBookIdAndCommentId.isEmpty()) {
            LOG.debug("************ getByBookIdAndByCommentId() ---> " +
                    format(EXCEPTION_MESSAGE_COMMENT_BY_BOOK_ID_AND_COMMENT_ID_NOT_EXIST, bookId, commentId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_COMMENT_BY_BOOK_ID_AND_COMMENT_ID_NOT_EXIST, bookId, commentId));
        }

        commentBookResult = mapEntityToDTO(commentByBookIdAndCommentId.get());

        LOG.debug("************ getByBookIdAndByCommentId() ---> commentBookListResult = " + commentBookResult);

        return commentBookResult;
    }

    /**
     * Deletes the {@literal CommentBookDTO} with the given {@code bookId} and by {@code commentId}.
     *
     * @param bookId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentId must not be {@literal null}, and {@code commentId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code bookId} is {@literal null}, and if {@code bookId} is equal or less zero.
     *                                  In case if the given {@code commentId} is {@literal null}, and if {@code commentId} is equal or less zero.
     * @throws BootstrapMethodError If book not exist with the {@code bookId}.
     * @throws CommentNotExistException If comment not exist with the {@code bookId} and {@code commentId}.
     */
    @Override
    public void deleteByBookIdAndByCommentId(Long bookId, Long commentId) {

        if ((bookId == null) || (bookId <= 0)
                || (commentId == null) || (commentId <= 0)) {
            LOG.debug("************ deleteByBookIdAndByCommentId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteByBookIdAndByCommentId() ---> bookId = " + bookId + " --> commentId = " + commentId);

        checkExistsBookById(bookId);
        checkExistsCommentByBookIdAndByCommentId(bookId, commentId);

        commentBookRepository.deleteByBookIdAndByCommentId(bookId, commentId);

        LOG.debug("************ deleteByBookIdAndByCommentId() ---> " +
                "Deleted comment by bookId = " + bookId + " and by commentId = " + commentId);
    }

    /**
     * Deletes all comments by {@code bookId}.
     *
     * @param bookId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @throws IllegalArgumentException In case if the given {@code bookId} is {@literal null}, and if {@code bookId} is equal or less zero.
     * @throws BookNotExistException If book not exist with the {@code bookId}.
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
        checkExistsCommentByBookId(bookId);

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

    private void checkExistsCommentByBookIdAndByCommentId(Long bookId, Long commentId) {
        if (commentBookRepository.findByBookIdAndCommentId(bookId, commentId).isEmpty()) {
            LOG.debug("************ checkExistsCommentByBookIdAndByCommentId() ---> "
                    + "Comment with this bookId = " + bookId + " and commentId = " + commentId + " not exist.");
            throw new CommentNotExistException(
                    "Comment with this bookId = " + bookId + " and commentId = " + commentId + " not exist.");
        }
    }

    private void checkExistsCommentByBookId(Long bookId) {
        if (commentBookRepository.findByBookId(bookId).isEmpty()) {
            LOG.debug("************ checkExistsCommentByBookId() ---> Comments with this bookId = " + bookId + " not exist.");
            throw new CommentNotExistException("Comments with this bookId = " + bookId + " not exist.");
        }
    }
}
