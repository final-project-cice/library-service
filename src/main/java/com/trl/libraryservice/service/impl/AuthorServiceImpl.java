package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.repository.AuthorRepository;
import com.trl.libraryservice.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENT = "Parameter '%s' is illegal, check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_AUTHOR_NOT_EXIST = "Author with this id = %s not exist.";
    private static final String EXCEPTION_MESSAGE_AUTHORS_BY_BOOK_ID_NOT_EXIST = "Authors with this bookId + %s not exist.";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
