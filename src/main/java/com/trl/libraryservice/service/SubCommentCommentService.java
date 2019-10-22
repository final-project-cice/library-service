package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface SubCommentCommentService {

    SubCommentCommentDTO create(SubCommentCommentDTO subCommentCommentDTO);


    SubCommentCommentDTO updateUser(Long id, UserDTO userDTO);

    SubCommentCommentDTO updateText(Long id, String text);

    SubCommentCommentDTO updateDate(Long id, LocalDate date);


    Boolean delete(Long id);


    SubCommentCommentDTO findById(Long id);

    List<SubCommentCommentDTO> findByUser(UserDTO userDTO);

    List<SubCommentCommentDTO> findByText(String text);

    List<SubCommentCommentDTO> findByDate(LocalDate date);

    List<SubCommentCommentDTO> findAll();
}
