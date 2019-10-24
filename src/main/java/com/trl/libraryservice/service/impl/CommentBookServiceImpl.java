package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.service.CommentBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentBookServiceImpl implements CommentBookService {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookServiceImpl.class);

    @Override
    public CommentBookDTO create(CommentBookDTO commentBookDTO) {
        return null;
    }

    @Transactional
    @Override
    public CommentBookDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Transactional
    @Override
    public CommentBookDTO updateText(Long id, String text) {
        return null;
    }

    @Transactional
    @Override
    public CommentBookDTO updateDate(Long id, LocalDate date) {
        return null;
    }

    @Transactional
    @Override
    public List<SubCommentCommentDTO> updateSubComment(Long id, SubCommentCommentDTO subCommentCommentDTO) {
        return null;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public CommentBookDTO findById(Long id) {
        return null;
    }

    @Override
    public List<CommentBookDTO> findByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<CommentBookDTO> findByText(String text) {
        return null;
    }

    @Override
    public List<CommentBookDTO> findByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<CommentBookDTO> findAll() {
        return null;
    }
}
