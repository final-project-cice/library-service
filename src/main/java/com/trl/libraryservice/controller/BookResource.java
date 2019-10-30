package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.exception.IllegalValueException;
import com.trl.libraryservice.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class BookResource {

    private static final Logger LOG = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> add(@RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        LOG.debug("************ add() ---> book = " + book);

        BookDTO resultService = null;

        try {
            resultService = bookService.add(book);
        } catch (IllegalMethodParameterException illegalMethodParameterException) {
            LOG.error("************ add() ---> " +
                    "Parameter is illegal, check the parameter that are passed to the method.", illegalMethodParameterException);
            return ResponseEntity.badRequest().build();
        } catch (IllegalValueException illegalValueException) {
            LOG.error("************ add() ---> "
                    + "One of the fields from parameter 'book' is incorrect, check the variables that it has the 'book'.", illegalValueException);
            return ResponseEntity.badRequest().build();
        }

        LOG.debug("************ add() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ addBook() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @LoadBalanced
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        ResponseEntity<BookDTO> response = null;

        LOG.debug("************ getById() ---> id = " + id);

        BookDTO resultService = null;
        try {
            resultService = bookService.getById(id);
        } catch (IllegalMethodParameterException illegalMethodParameterException) {
            LOG.error("************ getById() ---> "
                    + "Parameter is illegal, check the parameter that are passed to the method.", illegalMethodParameterException);
            return ResponseEntity.badRequest().build();
        }

        if (resultService == null) {
            LOG.debug("************ getById() ---> Book with this id = '" + id + "' not exist.");
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ getById() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ getById() ---> response = " + response);

        return response;
    }
}
