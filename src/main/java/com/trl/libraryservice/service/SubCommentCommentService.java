package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;

import org.springframework.data.domain.Page;

import java.util.List;

public interface SubCommentCommentService {

    SubCommentCommentDTO add(Long commentId, SubCommentCommentDTO subComment);


    SubCommentCommentDTO getBySubCommentId(Long subCommentId);

    Page<SubCommentCommentDTO> getPageOfSubCommentsByCommentId(Long commentId, Integer startPage, Integer pageSize);

    Page<SubCommentCommentDTO> getPageOfSortedSubCommentsByCommentId(Long commentId, Integer startPage, Integer pageSize, String sortOrder);


    SubCommentCommentDTO updateBySubCommentId(Long subCommentId, SubCommentCommentDTO subComment);


    SubCommentCommentDTO deleteBySubCommentId(Long subCommentId);

    List<SubCommentCommentDTO> deleteAllByCommentId(Long commentId);
}
