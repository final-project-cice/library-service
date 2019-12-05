package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.repository.AuthorRepository;
import com.trl.libraryservice.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class is designed to implement methods of service {@literal AuthorService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
