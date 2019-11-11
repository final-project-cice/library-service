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


    CommentBookDTO getById(Long commentId);

    List<CommentBookDTO> getAllByBookId(Long bookId);


    CommentBookDTO updateById(Long commentId, CommentBookDTO commentBook);


    void deleteById(Long commentId);

    void deleteAllByBookId(Long bookId);
}
