package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.exception.IllegalValueException;
import com.trl.libraryservice.repository.AuthorRepository;
import com.trl.libraryservice.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    /**
     * This method is designed to add the AuthorDTO.
     *
     * @return An object of type AuthorDTO.
     * @throws IllegalMethodParameterException If parameter 'book' is equal null.
     * @throws IllegalValueException           If one of the parameter fields is null, this exception will be thrown.
     */
    @Override
    public AuthorDTO add(AuthorDTO author) throws IllegalMethodParameterException {
        AuthorDTO authorResult = null;

        if (author == null) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ add() ---> author = " + author);

        /*if ((book.getName() == null) || (deleteWhitespace(book.getName()).isEmpty())
                || (book.getGenres() == null) || (book.getGenres().isEmpty())
                || (book.getPublishingHouse() == null)
                || (book.getPublicationDate() == null)
                || (book.getAuthors() == null) || book.getAuthors().isEmpty()) {
            LOG.debug("************ add() ---> "
                    + "One of the fields from parameter 'book' is incorrect, check the variables that it has the 'book'.");
            throw new IllegalValueException(
                    "One of the fields from parameter 'book' is incorrect, check the variables that it has the 'book'.");
        }

        AuthorEntity savedAuthor = bookRepository.save(BookConverter.mapDTOToEntity(book));


        LOG.debug("************ add() ---> savedBook = " + savedBook);

        bookResult = mapEntityToDTO(savedBook);*/

        LOG.debug("************ add() ---> authorResult = " + authorResult);

        return authorResult;
    }
}
