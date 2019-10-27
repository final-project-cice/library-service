package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserDTO create(UserDTO userDTO)
            throws InvalidArgumentException, InvalidObjectVariableValueException, UserWithTheEmailExistException;


    UserDTO updateFirstName(Long id, String firstName)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;

    UserDTO updateLastName(Long id, String lastName)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;

    UserDTO updateEmail(Long id, String email)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException, UserWithTheEmailExistException;

    UserDTO updateBirthday(Long id, LocalDate birthday)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException;


    Boolean delete(Long id) throws EntityNotFoundWithThisValueException, InvalidArgumentException;


    UserDTO findById(Long id)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<UserDTO> findByFirstName(String firstName)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<UserDTO> findByLastName(String lastName)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    UserDTO findByEmail(String email) throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<UserDTO> findByBirthday(LocalDate birthday)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException;

    List<UserDTO> findAll() throws EntitiesNotFoundException;
}
