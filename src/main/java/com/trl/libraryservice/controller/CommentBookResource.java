package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.service.CommentBookService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class CommentBookResource {

    private final CommentBookService commentBookService;

    public CommentBookResource(CommentBookService commentBookService) {
        this.commentBookService = commentBookService;
    }

    /**
     * Add the {@literal CommentBookDTO}.
     * If the {@code commentBook} have field subComments, this method not be added subComments.
     *
     * @param bookId      must not be equals to {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentBook must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)}.
     */
    @PostMapping(
            path = "/{bookId}/comments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentBookDTO> add(@PathVariable Long bookId, @RequestBody CommentBookDTO commentBook) {
        ResponseEntity<CommentBookDTO> response = null;

        commentBookService.add(bookId, commentBook);

        response = ResponseEntity.ok().build();

        return response;
    }

    /**
     * Retrieves the {@literal CommentBookDTO} by ths {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)} with the given {@code commentId}.
     */
    @GetMapping(
            path = "/comments/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentBookDTO> getById(@PathVariable Long commentId) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.getById(commentId);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieves all comments by this {@code bookId}.
     *
     * @param bookId must not be equals to {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(List<CommentBookDTO>)} with the given {@code bookId}.
     */
    @GetMapping(
            path = "/{bookId}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentBookDTO>> getAllByBookId(@PathVariable Long bookId) {
        ResponseEntity<List<CommentBookDTO>> response = null;

        List<CommentBookDTO> resultListService = commentBookService.getAllByBookId(bookId);

        response = ResponseEntity.ok(resultListService);

        return response;
    }

    /**
     * Update the {@literal CommentBookDTO} by this {@code id}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param commentBook must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)} with the given {@code commentId}.
     */
    @PostMapping(path = "/comments/{commentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentBookDTO> updateById(@PathVariable Long commentId, @RequestBody CommentBookDTO commentBook) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = null;

        resultService = commentBookService.updateById(commentId, commentBook);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal CommentBookDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal CommentBookDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteByCommentId(@PathVariable Long commentId) {
        ResponseEntity response = null;

        commentBookService.deleteById(commentId);

        response = ResponseEntity.ok().build();

        return response;
    }

    /**
     * Deletes all {@literal CommentBookDTO} with the given {@code bookId}.
     *
     * @param bookId must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal CommentBookDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/{bookId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAllByBookId(@PathVariable Long bookId) {
        ResponseEntity response = null;

        commentBookService.deleteAllByBookId(bookId);

        response = ResponseEntity.ok().build();

        return response;
    }
}
