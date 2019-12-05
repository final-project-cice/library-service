package com.trl.libraryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is designed to support controller layout for {@literal GenreAuthorDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "books/authors")
public class GenreAuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(GenreAuthorController.class);

}
