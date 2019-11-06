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
     * @param bookId      must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentBook must not be {@literal null}.
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
     * Retrieves all comments by this {@code bookId}.
     *
     * @param bookId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(List<CommentBookDTO>)} with the given {@code bookId}.
     */
    @GetMapping(
            path = "/{bookId}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentBookDTO>> getByBookId(@PathVariable Long bookId) {
        ResponseEntity<List<CommentBookDTO>> response = null;

        List<CommentBookDTO> resultListService = commentBookService.getByBookId(bookId);

        response = ResponseEntity.ok(resultListService);

        return response;
    }

    /**
     * Retrieves the {@literal CommentBookDTO} by this {@code bookId} and {@code commentId}.
     *
     * @param bookId    must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @param commentId must not be {@literal null}, and {@code bookId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)} with the given {@code bookId} and {@code commentId}.
     */
    @GetMapping(
            path = "/{bookId}/comments/{commentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentBookDTO> getByBookIdAndCommentId(@PathVariable Long bookId, @PathVariable Long commentId) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.getByBookIdAndByCommentId(bookId, commentId);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal CommentBookDTO} with the given {@code bookId} and {@code commentId}.
     *
     * @param bookId    must not be {@literal null}, and {@code id} must be greater than zero.
     * @param commentId must not be {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal CommentBookDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/{bookId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Long bookId, @PathVariable Long commentId) {
        ResponseEntity response = null;

        commentBookService.deleteByBookIdAndByCommentId(bookId, commentId);

        response = ResponseEntity.ok().build();

        return response;
    }
}
