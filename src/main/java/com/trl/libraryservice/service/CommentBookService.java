package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface CommentBookService {


    CommentBookDTO create(CommentBookDTO commentBookDTO);


    CommentBookDTO updateUser(Long id, UserDTO userDTO);

    CommentBookDTO updateText(Long id, String text);

    CommentBookDTO updateDate(Long id, LocalDate date);

    List<SubCommentCommentDTO> updateSubComment(Long id, SubCommentCommentDTO subCommentCommentDTO);


    Boolean delete(Long id);


    CommentBookDTO findById(Long id);

    List<CommentBookDTO> findByUser(UserDTO userDTO);

    List<CommentBookDTO> findByText(String text);

    List<CommentBookDTO> findByDate(LocalDate date);

    List<CommentBookDTO> findAll();
}
