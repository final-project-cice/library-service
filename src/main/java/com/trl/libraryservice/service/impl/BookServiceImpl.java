package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.exception.IllegalValueException;
import com.trl.libraryservice.repository.BookRepository;
import com.trl.libraryservice.repository.entity.BookEntity;
import com.trl.libraryservice.service.AuthorService;
import com.trl.libraryservice.service.BookService;
import com.trl.libraryservice.service.converter.BookConverter;

import static com.trl.libraryservice.service.converter.BookConverter.mapEntityToDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    /**
     * This method is designed to add the BookDTO.
     *
     * @return An object of type BookDTO.
     * @throws IllegalMethodParameterException If parameter 'book' is equal null.
     * @throws IllegalValueException           If one of the parameter fields is null, this exception will be thrown.
     */
    @Override
    public BookDTO add(BookDTO book) throws IllegalMethodParameterException, IllegalValueException {
        BookDTO bookResult = null;

        if (book == null) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ add() ---> book = " + book);

        if ((book.getName() == null) || (deleteWhitespace(book.getName()).isEmpty())
                || (book.getGenres() == null) || (book.getGenres().isEmpty())
                || (book.getPublishingHouse() == null)
                || (book.getPublicationDate() == null)
                || (book.getAuthors() == null) || book.getAuthors().isEmpty()) {
            LOG.debug("************ add() ---> "
                    + "One of the fields from parameter 'book' is incorrect, check the variables that it has the 'book'.");
            throw new IllegalValueException(
                    "One of the fields from parameter 'book' is incorrect, check the variables that it has the 'book'.");
        }

        BookEntity savedBook = bookRepository.save(BookConverter.mapDTOToEntity(book));


        LOG.debug("************ add() ---> savedBook = " + savedBook);

        bookResult = mapEntityToDTO(savedBook);

        LOG.debug("************ add() ---> bookResult = " + bookResult);

        return bookResult;
    }

    /**
     * This method is designed to get the BookDTO by 'id' value.
     *
     * @param id The search for a book by this 'id'. Parameter 'id' must be greater than zero.
     * @return An object of type BookDTO.
     * @throws IllegalMethodParameterException If parameter 'id' is equal or less zero.
     */
    @Override
    public BookDTO getById(Long id) throws IllegalMethodParameterException {
        BookDTO bookResult = null;

        if (id <= 0) {
            LOG.debug("************ getById() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ getById() ---> id = " + id);

        Optional<BookEntity> bookById = bookRepository.findById(id);
        LOG.debug("************ getById() ---> bookFromRepositoryById = " + bookById);

        if (bookById.isEmpty()) {
            LOG.debug("************ getById() ---> Book with this id = '" + id + "' not exist.");
            return bookResult;
        }

        bookResult = mapEntityToDTO(bookById.get());
        LOG.debug("************ getById() ---> bookResult = " + bookResult);

        return bookResult;
    }
}
