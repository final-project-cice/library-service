package com.trl.libraryservice.controller;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.service.SubCommentCommentService;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class SubCommentCommentController {

    private final SubCommentCommentService subCommentService;

    public SubCommentCommentController(SubCommentCommentService subCommentService) {
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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<SubCommentCommentDTO> add(@PathVariable Long commentId, @RequestBody SubCommentCommentDTO subComment) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.add(commentId, subComment);

        resultService.add(linkTo(methodOn(SubCommentCommentController.class).add(commentId, subComment))
                .withSelfRel());
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(null))
                .withRel("getBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(commentId, null, null))
                .withRel("getPageOfSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(commentId, null, null, null))
                .withRel("getPageOfSortedSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(null, null))
                .withRel("updateBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                .withRel("deleteBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(commentId))
                .withRel("deleteAllSubCommentsByCommentId"));

        response = ResponseEntity.status(HttpStatus.CREATED).body(resultService);

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
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<SubCommentCommentDTO> getBySubCommentId(@PathVariable Long subCommentId) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.getBySubCommentId(subCommentId);

        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(subCommentId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(null, null, null))
                .withRel("getPageOfSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(null, null, null, null))
                .withRel("getPageOfSortedSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(subCommentId, null))
                .withRel("updateBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                .withRel("deleteBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(null))
                .withRel("deleteAllSubCommentsByCommentId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Retrieve Page of {@literal SubCommentCommentDTO} by this {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal ResponseEntity.ok(List<SubCommentCommentDTO>)} with the given {@code commentId}.
     */
    @GetMapping(
            path = "/comments/{commentId}/subComments/{startPage}/{pageSize}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<SubCommentCommentDTO>> getPageOfSubCommentsByCommentId(
            @PathVariable Long commentId, @PathVariable Integer startPage, @PathVariable Integer pageSize) {
        ResponseEntity<Page<SubCommentCommentDTO>> response = null;

        Page<SubCommentCommentDTO> resultPageService = subCommentService.getPageOfSubCommentsByCommentId(commentId, startPage, pageSize);

        for (SubCommentCommentDTO subComment : resultPageService) {
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(commentId, startPage, pageSize))
                    .withSelfRel());
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).add(commentId, null))
                    .withRel("add"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(null))
                    .withRel("getBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(commentId, startPage, pageSize, null))
                    .withRel("getPageOfSortedSubCommentsByCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(null, null))
                    .withRel("updateBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                    .withRel("deleteBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(commentId))
                    .withRel("deleteAllSubCommentsByCommentId"));
        }

        response = ResponseEntity.ok(resultPageService);

        return response;
    }

    /**
     * Retrieve Page of {@literal SubCommentCommentDTO} by this {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted subComments will be. Must not be {@literal null}.
     * @return the {@literal ResponseEntity.ok(List<SubCommentCommentDTO>)} with the given {@code commentId}.
     */
    @GetMapping(
            path = "/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<Page<SubCommentCommentDTO>> getPageOfSortedSubCommentsByCommentId(
            @PathVariable Long commentId, @PathVariable Integer startPage, @PathVariable Integer pageSize, @PathVariable String sortOrder) {
        ResponseEntity<Page<SubCommentCommentDTO>> response = null;

        Page<SubCommentCommentDTO> resultPageService = subCommentService.getPageOfSortedSubCommentsByCommentId(commentId, startPage, pageSize, sortOrder);

        for (SubCommentCommentDTO subComment : resultPageService) {
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(commentId, startPage, pageSize, sortOrder))
                    .withSelfRel());
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).add(commentId, null))
                    .withRel("add"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(null))
                    .withRel("getBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(commentId, startPage, pageSize))
                    .withRel("getPageOfSubCommentsByCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(null, null))
                    .withRel("updateBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                    .withRel("deleteBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(commentId))
                    .withRel("deleteAllSubCommentsByCommentId"));
        }

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
    @PatchMapping(path = "/comments/subComments/{subCommentId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<SubCommentCommentDTO> updateBySubCommentId(@PathVariable Long subCommentId, @RequestBody SubCommentCommentDTO subComment) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.updateBySubCommentId(subCommentId, subComment);

        resultService.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(subCommentId, null))
                .withSelfRel());
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(subCommentId))
                .withRel("getBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(null, null, null))
                .withRel("getPageOfSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(null, null, null, null))
                .withRel("getPageOfSortedSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                .withRel("deleteBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(null))
                .withRel("deleteAllSubCommentsByCommentId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes the {@literal SubCommentCommentDTO} with the given {@code subCommentId}.
     *
     * @param subCommentId must not be equals to {@literal null}, and {@code subCommentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok(SubCommentCommentDTO)} if {@literal SubCommentCommentDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/comments/subComments/{subCommentId}",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<SubCommentCommentDTO> deleteBySubCommentId(@PathVariable Long subCommentId) {
        ResponseEntity<SubCommentCommentDTO> response = null;

        SubCommentCommentDTO resultService = subCommentService.deleteBySubCommentId(subCommentId);

        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(subCommentId))
                .withSelfRel());
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).add(null, null))
                .withRel("add"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(subCommentId))
                .withRel("getBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(null, null, null))
                .withRel("getPageOfSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(null, null, null, null))
                .withRel("getPageOfSortedSubCommentsByCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(subCommentId, null))
                .withRel("updateBySubCommentId"));
        resultService.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(null))
                .withRel("deleteAllSubCommentsByCommentId"));

        response = ResponseEntity.ok(resultService);

        return response;
    }

    /**
     * Deletes all {@literal SubCommentCommentDTO} with the given {@code commentId}.
     *
     * @param commentId must not be equals to {@literal null}, and {@code commentId} must be greater than zero.
     * @return the {@literal ResponseEntity.ok()} if {@literal SubCommentCommentDTO} is deleted correctly.
     */
    @DeleteMapping(
            path = "/comments/{commentId}/subComments",
            produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<List<SubCommentCommentDTO>> deleteAllSubCommentsByCommentId(@PathVariable Long commentId) {
        ResponseEntity<List<SubCommentCommentDTO>> response = null;

        List<SubCommentCommentDTO> resultService = subCommentService.deleteAllByCommentId(commentId);

        for (SubCommentCommentDTO subComment : resultService) {
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteAllSubCommentsByCommentId(commentId))
                    .withSelfRel());
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).add(commentId, null))
                    .withRel("add"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getBySubCommentId(null))
                    .withRel("getBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSubCommentsByCommentId(commentId, null, null))
                    .withRel("getPageOfSubCommentsByCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).getPageOfSortedSubCommentsByCommentId(commentId, null, null, null))
                    .withRel("getPageOfSortedSubCommentsByCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).updateBySubCommentId(null, null))
                    .withRel("updateBySubCommentId"));
            subComment.add(linkTo(methodOn(SubCommentCommentController.class).deleteBySubCommentId(null))
                    .withRel("deleteBySubCommentId"));
        }

        response = ResponseEntity.ok(resultService);

        return response;
    }
}
