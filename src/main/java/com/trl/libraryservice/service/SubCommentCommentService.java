package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface SubCommentCommentService {

    SubCommentCommentDTO create(SubCommentCommentDTO subComment) throws Exception;


    SubCommentCommentDTO updateUser(Long id, UserDTO user) throws Exception;

    SubCommentCommentDTO updateText(Long id, String text) throws Exception;

    SubCommentCommentDTO updateDate(Long id, LocalDate date) throws Exception;


    Boolean delete(Long id) throws Exception;


    SubCommentCommentDTO findById(Long id) throws Exception;

    List<SubCommentCommentDTO> findByUser(UserDTO user) throws Exception;

    List<SubCommentCommentDTO> findByText(String text) throws Exception;

    List<SubCommentCommentDTO> findByDate(LocalDate date) throws Exception;

    List<SubCommentCommentDTO> findAll() throws Exception;
}
