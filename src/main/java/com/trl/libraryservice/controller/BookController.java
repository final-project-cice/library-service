package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.service.BookService;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * This class is designed to support resource layout for {@literal BookDTO}.
 *
 * @author Tsyupryk Roman
 */
@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Add the {@literal BookDTO}.
     *
     * @param book must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(BookDTO)}.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BookDTO> add(@RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.add(book);

        resultService.add(linkTo(methodOn(BookController.class).add(null)).withSelfRel());
        resultService.add(linkTo(methodOn(BookController.class).getById(resultService.getBookId())).withRel("get"));
        resultService.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withRel("getByPage"));
        resultService.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withRel("getByPageAndSort"));
        resultService.add(linkTo(methodOn(BookController.class).update(resultService.getBookId(), null)).withRel("update"));
        resultService.add(linkTo(methodOn(BookController.class).deleteById(resultService.getBookId())).withRel("delete"));

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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.getById(id);

        resultService.add(linkTo(methodOn(BookController.class).getById(null)).withSelfRel());
        resultService.add(linkTo(methodOn(BookController.class).add(null)).withRel("add"));
        resultService.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withRel("getByPage"));
        resultService.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withRel("getByPageAndSort"));
        resultService.add(linkTo(methodOn(BookController.class).update(resultService.getBookId(), null)).withRel("update"));
        resultService.add(linkTo(methodOn(BookController.class).deleteById(resultService.getBookId())).withRel("delete"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieves all {@literal BookDTO} by paging.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<BookDTO>)}.
     */
    @GetMapping(
            path = "/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<BookDTO>> getByPage(@PathVariable Integer startPage, @PathVariable Integer pageSize) {
        ResponseEntity<Page<BookDTO>> response = null;

        Page<BookDTO> resultService = bookService.getByPage(startPage, pageSize);

        for (BookDTO book : resultService) {
            book.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withSelfRel());
            book.add(linkTo(methodOn(BookController.class).add(null)).withRel("add"));
            book.add(linkTo(methodOn(BookController.class).getById(book.getBookId())).withRel("get"));
            book.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withRel("getByPageAndSort"));
            book.add(linkTo(methodOn(BookController.class).update(book.getBookId(), null)).withRel("update"));
            book.add(linkTo(methodOn(BookController.class).deleteById(book.getBookId())).withRel("delete"));
        }

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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<BookDTO>> getByPageAndSort(@PathVariable Integer startPage, @PathVariable Integer pageSize, @PathVariable String sortOrder) {
        ResponseEntity<Page<BookDTO>> response = null;

        Page<BookDTO> resultService = bookService.getByPageAndSort(startPage, pageSize, sortOrder);

        for (BookDTO book : resultService) {
            book.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withSelfRel());
            book.add(linkTo(methodOn(BookController.class).add(null)).withRel("add"));
            book.add(linkTo(methodOn(BookController.class).getById(book.getBookId())).withRel("get"));
            book.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withRel("getByPage"));
            book.add(linkTo(methodOn(BookController.class).update(book.getBookId(), null)).withRel("update"));
            book.add(linkTo(methodOn(BookController.class).deleteById(book.getBookId())).withRel("delete"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the book by this {@code id}.
     *
     * @param id   must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @param book must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(Set<BookDTO>)}
     */
    @PatchMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO book) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.update(id, book);

        resultService.add(linkTo(methodOn(BookController.class).update(id, null)).withSelfRel());
        resultService.add(linkTo(methodOn(BookController.class).add(null)).withRel("add"));
        resultService.add(linkTo(methodOn(BookController.class).getById(id)).withRel("get"));
        resultService.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withRel("getByPage"));
        resultService.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withRel("getByPageAndSort"));
        resultService.add(linkTo(methodOn(BookController.class).deleteById(id)).withRel("delete"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal BookDTO} with the given {@code id}.
     *
     * @param id must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(BookDTO)} if {@literal BookDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/{id}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<BookDTO> deleteById(@PathVariable Long id) {
        ResponseEntity<BookDTO> response = null;

        BookDTO resultService = bookService.deleteById(id);

        resultService.add(linkTo(methodOn(BookController.class).deleteById(null)).withSelfRel());
        resultService.add(linkTo(methodOn(BookController.class).add(null)).withRel("add"));
        resultService.add(linkTo(methodOn(BookController.class).getById(id)).withRel("get"));
        resultService.add(linkTo(methodOn(BookController.class).getByPage(null, null)).withRel("getByPage"));
        resultService.add(linkTo(methodOn(BookController.class).getByPageAndSort(null, null, null)).withRel("getByPageAndSort"));
        resultService.add(linkTo(methodOn(BookController.class).update(null, null)).withRel("update"));

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
