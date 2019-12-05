package com.trl.libraryservice.controller;

import com.trl.libraryservice.service.AuthorService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is designed to support controller layout for {@literal AuthorDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/books")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
}
