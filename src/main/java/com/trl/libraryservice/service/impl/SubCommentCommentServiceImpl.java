package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.service.SubCommentCommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubCommentCommentServiceImpl implements SubCommentCommentService {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentServiceImpl.class);

    @Override
    public SubCommentCommentDTO create(SubCommentCommentDTO subCommentCommentDTO) {
        return null;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateText(Long id, String text) {
        return null;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateDate(Long id, LocalDate date) {
        return null;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public SubCommentCommentDTO findById(Long id) {
        return null;
    }

    @Override
    public List<SubCommentCommentDTO> findByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<SubCommentCommentDTO> findByText(String text) {
        return null;
    }

    @Override
    public List<SubCommentCommentDTO> findByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<SubCommentCommentDTO> findAll() {
        return null;
    }
}
