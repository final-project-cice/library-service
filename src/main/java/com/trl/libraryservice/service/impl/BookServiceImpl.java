package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.controller.dto.GenreBookDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.entity.BookEntity;
import com.trl.libraryservice.service.BookService;
import com.trl.libraryservice.service.converter.BookConverter;

import static com.trl.libraryservice.service.converter.BookConverter.mapEntityToDTO;
import static com.trl.libraryservice.service.converter.BookConverter.mapPageEntityToPageDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.time.LocalDate;
import java.util.List;
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
    private static final String EXCEPTION_MESSAGE_BOOKS_NOT_EXIST = "Books not found.";
    private static final String EXCEPTION_MESSAGE_THE_SAME_VALUE = "The value cannot be updated to the same value.";

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
     * Retrieve page of {@literal BookDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Set<BookDTO>}.
     * @throws DataNotFoundException in case if {@literal BookDTO} not exist.
     */
    @Override
    public Page<BookDTO> getByPage(int startPage, int pageSize) {
        Page<BookDTO> bookResult = null;

        Page<BookEntity> pagedResult = bookRepository.findAll(PageRequest.of(startPage, pageSize));
        LOG.debug("************ getByPage() ---> booksFromRepository = " + pagedResult);

        if (pagedResult.isEmpty()) {
            LOG.debug("************ getByPage() ---> " + EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
            throw new DataNotFoundException(EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
        }

        bookResult = mapPageEntityToPageDTO(pagedResult);
        LOG.debug("************ getByPage() ---> bookResult = " + bookResult);

        return bookResult;
    }

    /**
     * Retrieve page of sorted {@literal BookDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder must not be {@literal null}.
     * @return the {@literal Set<BookDTO>}.
     * @throws DataNotFoundException in case if {@literal BookDTO} not exist.
     */
    @Override
    public Page<BookDTO> getByPageAndSort(int startPage, int pageSize, String sortOrder) {
        Page<BookDTO> bookResult = null;

        Page<BookEntity> pagedResult = bookRepository.findAll(PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getByPageAndSort() ---> booksFromRepository = " + pagedResult);

        if (pagedResult.isEmpty()) {
            LOG.debug("************ getByPageAndSort() ---> " + EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
            throw new DataNotFoundException(EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
        }

        bookResult = mapPageEntityToPageDTO(pagedResult);
        LOG.debug("************ getByPageAndSort() ---> bookResult = " + bookResult);

        return bookResult;
    }

    /**
     * Update the {@literal BookDTO} by this {@code id}.
     *
     * @param id   must not be {@literal null}, and {@code id} must be greater than zero.
     * @param book must not be {@literal null}.
     * @return the {@literal BookDTO}.
     * @throws IllegalArgumentException in case the given {@code book} is {@literal null}.
     * @throws DataNotFoundException    in case if {@literal BookDTO} not exist by {@code id}.
     * @throws TheSameValueException    in case if {@code name} equals to name book.
     */
    @Override
    public BookDTO update(Long id, BookDTO book) {
        BookDTO bookResult = null;

        if ((id == null) || (id <= 0) || (book == null)) {
            LOG.debug("************ update() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT);
        }

        Optional<BookEntity> bookFromRepositoryById = bookRepository.findById(id);

        if (bookFromRepositoryById.isEmpty()) {
            LOG.debug("************ update() ---> " + EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
            throw new DataNotFoundException(EXCEPTION_MESSAGE_BOOKS_NOT_EXIST);
        }

        updateFields(id, mapEntityToDTO(bookFromRepositoryById.get()), book);

        bookResult = mapEntityToDTO(bookRepository.findById(id).get());

        LOG.debug("************ update() ---> Book by id = " + id + " is updated ---> " + bookResult);

        return bookResult;
    }

    /**
     * Deletes the {@literal BookDTO} with the given {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}, and if {@code id} is equal or less zero.
     * @throws DataNotFoundException    in case if {@literal BookDTO} not exist with this {@code id}.
     */
    @Override
    public BookDTO deleteById(Long id) {
        BookDTO bookResult = null;

        if ((id == null) || (id <= 0)) {
            LOG.debug("************ deleteById() ---> " + format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
            throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT, id));
        }

        LOG.debug("************ deleteById() ---> id = " + id);

        Optional<BookEntity> bookById = bookRepository.findById(id);
        LOG.debug("************ deleteById() ---> bookFromRepositoryById = " + bookById);

        if (bookById.isEmpty()) {
            LOG.debug("************ deleteById() ---> " + format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
            throw new BookNotExistException(format(EXCEPTION_MESSAGE_BOOK_NOT_EXIST, id));
        }

        bookResult = mapEntityToDTO(bookById.get());

        bookRepository.deleteById(id);

        LOG.debug("************ deleteById() ---> Deleted book = " + bookResult);

        return bookResult;
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

    public void updateFields(Long id, BookDTO actualBook, BookDTO sourceBook) {

        if ((sourceBook.getName() != null) && (!deleteWhitespace(sourceBook.getName()).isEmpty())) {
            updateName(id, actualBook.getName(), sourceBook.getName());
        }

        // TODO: Terminar this method. Imlementar functionality of update fields.
//        if ((sourceBook.getGenres() != null) && (!sourceBook.getGenres().isEmpty())){
//            updateGenres(id, actualBook.getGenres(), sourceBook.getGenres());
//        }

        if (sourceBook.getPublicationDate() != null) {
            updatePublishingDate(id, actualBook.getPublicationDate(), sourceBook.getPublicationDate());
        }
    }

    public void updateName(Long id, String actualName, String sourceName) {

        if (sourceName.equals(actualName)) {
            LOG.debug("************ updateName() ---> " + EXCEPTION_MESSAGE_THE_SAME_VALUE);
            throw new TheSameValueException(EXCEPTION_MESSAGE_THE_SAME_VALUE);
        }

        bookRepository.updateName(id, sourceName);
        LOG.debug("************ updateName() ---> Book name updated.");
    }

    public void updateGenres(Long id, List<GenreBookDTO> actualGenres, List<GenreBookDTO> sourceGenres) {

        if (sourceGenres.equals(actualGenres)) {
            LOG.debug("************ updateGenres() ---> " + EXCEPTION_MESSAGE_THE_SAME_VALUE);
            throw new TheSameValueException(EXCEPTION_MESSAGE_THE_SAME_VALUE);
        }

        // TODO: Terminar this method. Imlementar functionality of update genres.
        throw new FunctionalityNotImplementedException("This functionality not terminated");
    }

    public void updatePublishingDate(Long id, LocalDate actualPublishingDate, LocalDate sourcePublishingDate) {

        if (sourcePublishingDate.equals(actualPublishingDate)) {
            LOG.debug("************ updatePublishingDate() ---> " + EXCEPTION_MESSAGE_THE_SAME_VALUE);
            throw new TheSameValueException(EXCEPTION_MESSAGE_THE_SAME_VALUE);
        }

        bookRepository.updatePublicationDate(id, sourcePublishingDate);
        LOG.debug("************ updatePublishingDate() ---> Book publishing date updated.");
    }
}