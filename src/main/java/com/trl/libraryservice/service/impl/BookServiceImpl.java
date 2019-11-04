package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.DataNotFoundException;
import com.trl.libraryservice.exception.IllegalValueException;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.entity.BookEntity;
import com.trl.libraryservice.service.BookService;
import com.trl.libraryservice.service.converter.BookConverter;

import static com.trl.libraryservice.service.converter.BookConverter.mapEntityToDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.Optional;

/**
 * This class is designed to support service layout for {@literal BookDTO}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT = "Parameter '%s' is illegal, check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_BOOK_NOT_EXIST = "Book with this id = %s not exist.";

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Add the {@literal BookDTO}.
     *
     * @param book must not be {@literal null}.
     * @return The {@literal BookDTO}.
     * @throws IllegalArgumentException in case the given {@code book} is {@literal null},
     * @throws IllegalValueException    in case if one of the parameter fields is {@literal null}
     */
    @Override
    public BookDTO add(BookDTO book) {
        BookDTO bookResult = null;

        if (book == null) {
            LOG.debug("************ add() ---> " + format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, "null"));
            throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, "null"));
        }

        checkParameterBook(book);

        BookEntity savedBook = bookRepository.save(BookConverter.mapDTOToEntity(book));


        LOG.debug("************ add() ---> savedBook = " + savedBook);

        bookResult = mapEntityToDTO(savedBook);

        LOG.debug("************ add() ---> bookResult = " + bookResult);

        return bookResult;
    }

    /**
     * Retrieves the {@literal BookDTO} by this {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal BookDTO} with the given {@code id}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}, and if {@code id} is equal or less zero.
     * @throws DataNotFoundException    in case if {@literal BookDTO} not exist with this {@code id}.
     */
    @Override
    public BookDTO getById(Long id) {
        BookDTO bookResult = null;

        if ((id == null) || (id <= 0)) {
            LOG.debug("************ getById() ---> " + format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
            throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
        }

        LOG.debug("************ getById() ---> id = " + id);

        Optional<BookEntity> bookById = bookRepository.findById(id);
        LOG.debug("************ getById() ---> bookFromRepositoryById = " + bookById);

        if (bookById.isEmpty()) {
            LOG.debug("************ getById() ---> " + format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
        }

        bookResult = mapEntityToDTO(bookById.get());
        LOG.debug("************ getById() ---> bookResult = " + bookResult);

        return bookResult;
    }

    /**
     * Update the {@literal BookDTO} by this {@code id}.
     *
     * @param id   must not be {@literal null}, and {@code id} must be greater than zero.
     * @param book must not be {@literal null}.
     * @return The {@literal BookDTO}.
     * @throws IllegalArgumentException in case the given {@code book} is {@literal null},
     */
    @Override
    public BookDTO updateById(Long id, BookDTO book) {
        BookDTO bookResult = null;

        if ((id == null) || (id <= 0) || (book == null)) {
            LOG.debug("************ updateById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT);
        }

        // TODO: Finish this method.

        return bookResult;
    }

    /**
     * Deletes the {@literal BookDTO} with the given {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}, and if {@code id} is equal or less zero.
     * @throws DataNotFoundException    in case if {@literal BookDTO} not exist with this {@code id}.
     */
    @Transactional
    @Override
    public void deleteById(Long id) {

        if ((id == null) || (id <= 0)) {
            LOG.debug("************ deleteById() ---> " + format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
            throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
        }

        LOG.debug("************ deleteById() ---> id = " + id);

        Optional<BookEntity> bookById = bookRepository.findById(id);
        LOG.debug("************ deleteById() ---> bookFromRepositoryById = " + bookById);

        if (bookById.isEmpty()) {
            LOG.debug("************ deleteById() ---> " + format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
        }

        bookRepository.deleteById(id);

        LOG.debug("************ deleteById() ---> Deleted book by id = " + id);
    }

    private void checkParameterBook(BookDTO book) {
        String message = "Field '%s' is illegal, check the field that it has the 'book' parameter.";

        if (book.getName() == null) {
            LOG.debug("************ add() ---> " + format(message, "book.getName() == null"));
            throw new IllegalValueException(format(message, "book.getName() == null"));
        } else if (deleteWhitespace(book.getName()).isEmpty()) {
            LOG.debug("************ add() ---> " + format(message, "book.getName() is empty"));
            throw new IllegalValueException(format(message, "book.getName() == is empty"));
        }

        if (book.getGenres() == null) {
            LOG.debug("************ add() ---> " + format(message, "book.getGenres() == null"));
            throw new IllegalValueException(format(message, "book.getGenres() == null"));
        } else if (book.getGenres().isEmpty()) {
            LOG.debug("************ add() ---> " + format(message, "book.getGenres() is empty"));
            throw new IllegalValueException(format(message, "book.getGenres() is empty"));
        }

        if (book.getPublishingHouse() == null) {
            LOG.debug("************ add() ---> " + format(message, "book.getPublishingHouse() == null"));
            throw new IllegalValueException(format(message, "book.getPublishingHouse() == null"));
        }

        if (book.getPublicationDate() == null) {
            LOG.debug("************ add() ---> " + format(message, "book.getPublicationDate() == null"));
            throw new IllegalValueException(format(message, "book.getPublicationDate() == null"));
        }

        if (book.getAuthors() == null) {
            LOG.debug("************ add() ---> " + format(message, "book.getAuthors() == null"));
            throw new IllegalValueException(format(message, "book.getAuthors() == null"));
        } else if (book.getAuthors().isEmpty()) {
            LOG.debug("************ add() ---> " + format(message, "book.getAuthors() is empty"));
            throw new IllegalValueException(format(message, "book.getAuthors() is empty"));
        }
    }
}