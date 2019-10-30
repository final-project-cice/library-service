package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;

public interface AuthorService {

    AuthorDTO add(AuthorDTO author) throws IllegalMethodParameterException;

}
