package com.trl.libraryservice.service;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.exception.UserWithValueNotExistException;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserDTO create(UserDTO userDTO) throws Exception;


    UserDTO updateFirstName(Long id, String firstName) throws Exception;

    UserDTO updateLastName(Long id, String lastName) throws Exception;

    UserDTO updateEmail(Long id, String email) throws Exception;

    UserDTO updateBirthday(Long id, LocalDate birthday) throws Exception;


    Boolean delete(Long id) throws UserWithValueNotExistException, InvalidArgumentException;


    UserDTO findById(Long id) throws Exception;

    List<UserDTO> findByFirstName(String firstName) throws Exception;

    List<UserDTO> findByLastName(String lastName) throws Exception;

    List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) throws Exception;

    UserDTO findByEmail(String email) throws Exception;

    List<UserDTO> findByBirthday(LocalDate birthday) throws Exception;

    List<UserDTO> findAll() throws Exception;
}
