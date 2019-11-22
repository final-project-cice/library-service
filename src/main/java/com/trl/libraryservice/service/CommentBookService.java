package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.CommentBookDTO;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This interface is designed to support service for {@literal CommentBookDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface CommentBookService {

    CommentBookDTO add(Long bookId, CommentBookDTO commentBook);


    CommentBookDTO getByCommentId(Long commentId);

    Page<CommentBookDTO> getPageOfCommentsByBookId(Long bookId, Integer startPage, Integer pageSize);

    Page<CommentBookDTO> getPageOfSortedCommentsByBookId(Long bookId, Integer startPage, Integer pageSize, String sortOrder);


    CommentBookDTO updateByCommentId(Long commentId, CommentBookDTO commentBook);


    CommentBookDTO deleteByCommentId(Long commentId);

    List<CommentBookDTO> deleteAllCommentsByBookId(Long bookId);
}
