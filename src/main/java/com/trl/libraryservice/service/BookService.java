package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.BookDTO;

import org.springframework.data.domain.Page;

/**
 * This interface is designed to support service for {@literal BookDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface BookService {

    BookDTO add(BookDTO book);

    BookDTO getById(Long id);

    Page<BookDTO> getPageOfBooks(int startPage, int pageSize);

    Page<BookDTO> getPageOfSortedBooks(int startPage, int pageSize, String sortOrder);

    BookDTO update(Long id, BookDTO book);

    BookDTO deleteById(Long id);
}
