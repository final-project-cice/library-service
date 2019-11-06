package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.CommentBookDTO;

import java.util.List;

/**
 * This interface is designed to support service for {@literal CommentBookDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface CommentBookService {

    void add(Long bookId, CommentBookDTO commentBook);

    List<CommentBookDTO> getByBookId(Long bookId);

    CommentBookDTO getByBookIdAndByCommentId(Long bookId, Long commentId);

    void deleteByBookIdAndByCommentId(Long bookId, Long commentId);

    void deleteAllByBookId(Long bookId);
}
