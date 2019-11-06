package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.service.BookService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is designed to support resource layout for {@literal BookDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/books")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Add the {@literal BookDTO}.
     *
     * @param book must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(BookDTO)}.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> add(@RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.add(book);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieves the {@literal BookDTO)} by this {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BookDTO)} with the given {@code id}.
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.getById(id);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the {@literal BookDTO)} by this {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BookDTO)} with the given {@code id}.
     */
    @PostMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateById(@PathVariable Long id, @RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = null;

        resultService = bookService.updateById(id, book);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal BookDTO} with the given {@code id}.
     *
     * @param id must not be {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal BookDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Long id) {
        ResponseEntity response = null;

        bookService.deleteById(id);

        response = ResponseEntity.ok().build();

        return response;
    }
}
