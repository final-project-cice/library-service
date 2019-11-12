package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.BookDTO;

import org.springframework.data.domain.Page;

public interface BookService {

    BookDTO add(BookDTO book);

    BookDTO getById(Long id);

    Page<BookDTO> getAll(int startPage, int pageSize);

    Page<BookDTO> getAllAndSort(int startPage, int pageSize, String sortOrder);

    BookDTO updateById(Long id, BookDTO book);

    void deleteById(Long id);
}
