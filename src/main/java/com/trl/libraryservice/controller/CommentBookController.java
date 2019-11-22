package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.service.CommentBookService;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class CommentBookController {

    private final CommentBookService commentBookService;

    public CommentBookController(CommentBookService commentBookService) {
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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<CommentBookDTO> add(@PathVariable Long bookId, @RequestBody CommentBookDTO commentBook) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.add(bookId, commentBook);

        resultService.add(linkTo(methodOn(CommentBookController.class).add(bookId, null))
                .withSelfRel());
        resultService.add(linkTo(methodOn(CommentBookController.class).getByCommentId(resultService.getCommentId()))
                .withRel("getByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(bookId, null, null))
                .withRel("getPageOfCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(bookId, null, null, null))
                .withRel("getPageOfSortedCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(resultService.getCommentId(), null))
                .withRel("updateByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(resultService.getCommentId()))
                .withRel("deleteByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(bookId))
                .withRel("deleteAllCommentsByBookId"));

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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<CommentBookDTO> getByCommentId(@PathVariable Long commentId) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.getByCommentId(commentId);

        resultService.add(linkTo(methodOn(CommentBookController.class).getByCommentId(commentId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(CommentBookController.class).add(resultService.getBook().getBookId(), null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(resultService.getBook().getBookId(), null, null))
                .withRel("getPageOfCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(resultService.getBook().getBookId(), null, null, null))
                .withRel("getPageOfSortedCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(commentId, null))
                .withRel("updateByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(commentId))
                .withRel("deleteByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(resultService.getBook().getBookId()))
                .withRel("deleteAllCommentsByBookId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of {@literal CommentBookDTOs} by this {@code bookId}.
     *
     * @param bookId    must not be equals to {@literal null}, and {@code bookId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<CommentBookDTO>)} with the given {@code bookId}.
     */
    @GetMapping(
            path = "/{bookId}/comments/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<CommentBookDTO>> getPageOfCommentsByBookId(@PathVariable Long bookId,
                                                                          @PathVariable Integer startPage,
                                                                          @PathVariable Integer pageSize) {
        ResponseEntity<Page<CommentBookDTO>> response = null;

        Page<CommentBookDTO> resultService = commentBookService.getPageOfCommentsByBookId(bookId, startPage, pageSize);

        for (CommentBookDTO comment : resultService) {
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(bookId, null, null))
                    .withSelfRel());
            comment.add(linkTo(methodOn(CommentBookController.class).add(bookId, null))
                    .withRel("add"));
            comment.add(linkTo(methodOn(CommentBookController.class).getByCommentId(comment.getCommentId()))
                    .withRel("getByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(bookId, null, null, null))
                    .withRel("getPageOfSortedCommentsByBookId"));
            comment.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(comment.getCommentId(), null))
                    .withRel("updateByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(comment.getCommentId()))
                    .withRel("deleteByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(bookId))
                    .withRel("deleteAllCommentsByBookId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve page of {@literal CommentBookDTOs} by this {@code bookId}.
     *
     * @param bookId    must not be equals to {@literal null}, and {@code bookId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(Page<CommentBookDTO>)} with the given {@code bookId}.
     */
    @GetMapping(
            path = "/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<CommentBookDTO>> getPageOfSortedCommentsByBookId(@PathVariable Long bookId,
                                                                                @PathVariable Integer startPage,
                                                                                @PathVariable Integer pageSize,
                                                                                @PathVariable String sortOrder) {
        ResponseEntity<Page<CommentBookDTO>> response = null;

        Page<CommentBookDTO> resultService = commentBookService.getPageOfSortedCommentsByBookId(bookId, startPage, pageSize, sortOrder);

        for (CommentBookDTO comment : resultService) {
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(bookId, null, null, null))
                    .withSelfRel());
            comment.add(linkTo(methodOn(CommentBookController.class).add(bookId, null))
                    .withRel("add"));
            comment.add(linkTo(methodOn(CommentBookController.class).getByCommentId(comment.getCommentId()))
                    .withRel("getByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(bookId, null, null))
                    .withRel("getPageOfCommentsByBookId"));
            comment.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(comment.getCommentId(), null))
                    .withRel("updateByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(comment.getCommentId()))
                    .withRel("deleteByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(bookId))
                    .withRel("deleteAllCommentsByBookId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Update the {@literal CommentBookDTO} by this {@code id}.
     *
     * @param commentId   must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param commentBook must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)} with the given {@code commentId}.
     */
    @PostMapping(path = "/comments/{commentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentBookDTO> updateByCommentId(@PathVariable Long commentId, @RequestBody CommentBookDTO commentBook) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.updateByCommentId(commentId, commentBook);

        resultService.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(commentId, null))
                .withSelfRel());
        resultService.add(linkTo(methodOn(CommentBookController.class).add(resultService.getBook().getBookId(), null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getByCommentId(commentId))
                .withRel("getByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(resultService.getBook().getBookId(), null, null))
                .withRel("getPageOfCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(resultService.getBook().getBookId(), null, null, null))
                .withRel("getPageOfSortedCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(commentId))
                .withRel("deleteByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(resultService.getBook().getBookId()))
                .withRel("deleteAllCommentsByBookId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal CommentBookDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(CommentBookDTO)} if {@literal CommentBookDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/comments/{commentId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<CommentBookDTO> deleteByCommentId(@PathVariable Long commentId) {
        ResponseEntity<CommentBookDTO> response = null;

        CommentBookDTO resultService = commentBookService.deleteByCommentId(commentId);

        resultService.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(commentId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(CommentBookController.class).add(resultService.getBook().getBookId(), null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getByCommentId(commentId))
                .withRel("getByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(resultService.getBook().getBookId(), null, null))
                .withRel("getPageOfCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(resultService.getBook().getBookId(), null, null, null))
                .withRel("getPageOfSortedCommentsByBookId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(commentId, null))
                .withRel("updateByCommentId"));
        resultService.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(resultService.getBook().getBookId()))
                .withRel("deleteAllCommentsByBookId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes all {@literal CommentBookDTO} with the given {@code bookId}.
     *
     * @param bookId must not be equals to {@literal null}, and {@code id} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(List<CommentBookDTO>)} if {@literal List<CommentBookDTO>} is deleted correctly.
     */
    @DeleteMapping(
            path = "/{bookId}/comments",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<List<CommentBookDTO>> deleteAllCommentsByBookId(@PathVariable Long bookId) {
        ResponseEntity<List<CommentBookDTO>> response = null;

        List<CommentBookDTO> resultService = commentBookService.deleteAllCommentsByBookId(bookId);

        for (CommentBookDTO comment : resultService) {
            comment.add(linkTo(methodOn(CommentBookController.class).deleteAllCommentsByBookId(bookId))
                    .withSelfRel());
            comment.add(linkTo(methodOn(CommentBookController.class).add(bookId, null))
                    .withRel("add"));
            comment.add(linkTo(methodOn(CommentBookController.class).getByCommentId(comment.getCommentId()))
                    .withRel("getByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfCommentsByBookId(bookId, null, null))
                    .withRel("getPageOfCommentsByBookId"));
            comment.add(linkTo(methodOn(CommentBookController.class).getPageOfSortedCommentsByBookId(bookId, null, null, null))
                    .withRel("getPageOfSortedCommentsByBookId"));
            comment.add(linkTo(methodOn(CommentBookController.class).updateByCommentId(comment.getCommentId(), null))
                    .withRel("updateByCommentId"));
            comment.add(linkTo(methodOn(CommentBookController.class).deleteByCommentId(comment.getCommentId()))
                    .withRel("deleteByCommentId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
