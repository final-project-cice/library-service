package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;

import java.time.LocalDate;
import java.util.List;

public interface SubCommentCommentService {

    SubCommentCommentDTO create(SubCommentCommentDTO subComment)
            throws InvalidArgumentException, InvalidObjectVariableValueException;


    SubCommentCommentDTO updateUser(Long id, UserDTO user)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;

    SubCommentCommentDTO updateText(Long id, String text)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;

    SubCommentCommentDTO updateDate(Long id, LocalDate date)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;


    Boolean delete(Long id) throws InvalidArgumentException, EntityNotFoundWithThisValueException;


    SubCommentCommentDTO findById(Long id) throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<SubCommentCommentDTO> findByUser(UserDTO user)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<SubCommentCommentDTO> findByText(String text)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<SubCommentCommentDTO> findByDate(LocalDate date)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<SubCommentCommentDTO> findAll() throws EntitiesNotFoundException;
}
