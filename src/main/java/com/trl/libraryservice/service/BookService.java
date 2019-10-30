package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.exception.IllegalValueException;

public interface BookService {

    BookDTO add(BookDTO book) throws IllegalMethodParameterException, IllegalValueException;

    BookDTO getById(Long id) throws IllegalMethodParameterException;

}
