package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.GenreBookDTO;
import com.trl.libraryservice.repository.GenreBookRepository;
import com.trl.libraryservice.service.GenreBookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GenreBookServiceImpl implements GenreBookService {

    private static final Logger LOG = LoggerFactory.getLogger(GenreBookServiceImpl.class);

    private final GenreBookRepository genreBookRepository;

    public GenreBookServiceImpl(GenreBookRepository genreBookRepository) {
        this.genreBookRepository = genreBookRepository;
    }

    @Override
    public GenreBookDTO add(GenreBookDTO genreBook) {
        return null;
    }
}
