package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.BookDTO;

import java.util.Set;

public interface BookService {

    BookDTO add(BookDTO book);

    BookDTO getById(Long id);

    Set<BookDTO> getAll();

    BookDTO updateById(Long id, BookDTO book);

    void deleteById(Long id);
}
