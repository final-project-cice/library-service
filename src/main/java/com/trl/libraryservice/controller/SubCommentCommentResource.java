package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.service.SubCommentCommentService;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
public class SubCommentCommentResource {

    private final SubCommentCommentService subCommentService;

    public SubCommentCommentResource(SubCommentCommentService subCommentService) {
        this.subCommentService = subCommentService;
    }

    /**
     * Add the {@literal SubCommentCommentDTO}.
     *
     * @param commentId  must not be equal to {@literal null}, and {@code commentId} must be greater than zero.
     * @param subComment must not be equal to {@literal null}.
     * @return the {@literal ResponseEntity.ok(SubCommentCommentDTO)}.
     */
    @PostMapping(
            path = "/comments/{commentId}/subComments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCommentCommentDTO> add(@PathVariable Long commentId, @RequestBody SubCommentCommentDTO subComment) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        subCommentService.add(commentId, subComment);

        response = ResponseEntity.ok().build();

        return response;
    }

    /**
     * Retrieves the {@literal SubCommentCommentDTO} by ths {@code subCommentId}.
     *
     * @param subCommentId must not be equals to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(SubCommentCommentDTO)} with the given {@code subCommentId}.
     */
    @GetMapping(
            path = "/comments/subComments/{subCommentId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCommentCommentDTO> getById(@PathVariable Long subCommentId) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.getById(subCommentId);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve Page of {@literal SubCommentCommentDTO} by this {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(List<SubCommentCommentDTO>)} with the given {@code commentId}.
     */
    @GetMapping(
            path = "/comments/{commentId}/subComments/{startPage}/{pageSize}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SubCommentCommentDTO>> getAllByCommentId(
            @PathVariable Long commentId, @PathVariable Integer startPage, @PathVariable Integer pageSize) {
        ResponseEntity<Page<SubCommentCommentDTO>> response = null;

        Page<SubCommentCommentDTO> resultPageService = subCommentService.getAllByCommentId(commentId, startPage, pageSize);

        response = ResponseEntity.ok(resultPageService);

        return response;
    }

    /**
     * Update the {@literal SubCommentCommentDTO} by this {@code subCommentId}.
     *
     * @param subCommentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param subComment   must not be equals to {@literal null}.
     * @return the {@literal ResponseEntity.ok(SubCommentCommentDTO)} with the given {@code subCommentId}.
     */
    @PostMapping(path = "/comments/subComments/{subCommentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCommentCommentDTO> updateById(@PathVariable Long subCommentId, @RequestBody SubCommentCommentDTO subComment) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.updateById(subCommentId, subComment);

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     *
     * @param subCommentId must not be equals to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal SubCommentCommentDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/comments/subComments/{subCommentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Long subCommentId) {
        ResponseEntity response = null;

        subCommentService.deleteById(subCommentId);

        response = ResponseEntity.ok().build();

        return response;
    }

    /**
     * Deletes all {@literal SubCommentCommentDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal SubCommentCommentDTO} is deleted correctly.
     */
    @DeleteMapping(path = "/comments/{commentId}/subComments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAllByCommentId(@PathVariable Long commentId) {
        ResponseEntity response = null;

        subCommentService.deleteAllByCommentId(commentId);

        response = ResponseEntity.ok().build();

        return response;
    }
}
