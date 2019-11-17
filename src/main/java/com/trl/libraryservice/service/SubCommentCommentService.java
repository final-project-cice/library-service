package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;

import org.springframework.data.domain.Page;

public interface SubCommentCommentService {

    void add(Long commentId, SubCommentCommentDTO subComment);


    SubCommentCommentDTO getById(Long subCommentId);

    Page<SubCommentCommentDTO> getAllByCommentId(Long commentId, Integer startPage, Integer pageSize);


    SubCommentCommentDTO updateById(Long subCommentId, SubCommentCommentDTO subComment);


    void deleteById(Long subCommentId);

    void deleteAllByCommentId(Long commentId);
}
