package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;

import java.util.List;

public interface SubCommentCommentService {

    void add(Long commentId, SubCommentCommentDTO subComment);


    SubCommentCommentDTO getById(Long subCommentId);

    List<SubCommentCommentDTO> getAllByCommentId(Long commentId);


    SubCommentCommentDTO updateById(Long subCommentId, SubCommentCommentDTO subComment);


    void deleteById(Long subCommentId);

    void deleteAllByCommentId(Long commentId);
}
