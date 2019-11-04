package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.BookDTO;

public interface BookService {

    BookDTO add(BookDTO book);

    BookDTO getById(Long id);

    BookDTO updateById(Long id, BookDTO book);

    void deleteById(Long id);

}
