package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.service.BookService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
     * @param book must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(BookDTO)}.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> add(@RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.add(book);

        response = ResponseEntity.status(HttpStatus.CREATED).body(resultService);

        return response;
    }

    /**
     * Retrieves the {@literal BookDTO} by this {@code id}.
     *
     * @param id must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BookDTO)} with the given {@code id}.
     */
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.getById(id);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieves all {@literal BookDTO} by paging.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<BookDTO>)}.
     */
    @GetMapping(
            path = "/{startPage}/{pageSize}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BookDTO>> getAll(@PathVariable Integer startPage, @PathVariable Integer pageSize) {
        ResponseEntity<Page<BookDTO>> response = null;

        Page<BookDTO> resultService = bookService.getAll(startPage, pageSize);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieves all {@literal BookDTO} by paging and sort.
     *
     * @return the {@literal ResponseEntity.ok(Set<BookDTO>)}.
     */
    @GetMapping(
            path = "/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BookDTO>> getAllAndSort(@PathVariable Integer startPage, @PathVariable Integer pageSize, @PathVariable String sortOrder) {
        ResponseEntity<Page<BookDTO>> response = null;

        Page<BookDTO> resultService = bookService.getAllAndSort(startPage, pageSize, sortOrder);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the {@literal BookDTO)} by this {@code id}.
     *
     * @param id must not be equals to {@literal null}, and {@code id} must be greater than zero.
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
     * @param id must not be equals to {@literal null}, and {@code id} must be greater than zero.
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
