package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.UserRepository;
import com.trl.libraryservice.repository.entity.UserEntity;
import com.trl.libraryservice.service.UserService;
import com.trl.libraryservice.service.converter.UserConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO user) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ create() ---> user = " + user);

        if (user == null) {
            LOG.debug("************ create() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        // TODO: This operation is very resource-intensive. I'm not sure whether to do it.
        if ((user.getId() <= 0)
                || (user.getFirstName() == null) || (deleteWhitespace(user.getFirstName()).isEmpty())
                || (user.getLastName() == null) || (deleteWhitespace(user.getLastName()).isEmpty())
                || (user.getEmail() == null) || (deleteWhitespace(user.getEmail()).isEmpty())) {
            LOG.debug("************ create() ---> " +
                    "One of the variable from parameter 'user' is incorrect, check the variables that it has the 'user'.");
            throw new InvalidVariableOfObjectException(
                    "One of the variable from parameter 'user' is incorrect, check the variables that it has the 'user'.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOG.debug("************ create() ---> User with this email = '" + user.getEmail()
                    + "' exist. It is not allowed to register multiple users with the same email.");
            throw new UserWithEmailExistException("User with this email = '" + user.getEmail()
                    + "' exist. It is not allowed to register multiple users with the same email.");
        }

        UserEntity savedUser = userRepository.save(UserConverter.mapDTOToEntity(user));

        LOG.debug("************ create() ---> savedUser = " + savedUser);

        userResult = UserConverter.mapEntityToDTO(savedUser);

        LOG.debug("************ create() ---> userResult = " + userResult);

        return userResult;
    }

    @Transactional
    @Override
    public UserDTO updateFirstName(Long id, String firstName) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ updateFirstName() ---> id = " + id + " ---> firstName = " + firstName);

        if ((id <= 0) || (firstName == null) || (deleteWhitespace(firstName).isEmpty())) {
            LOG.debug("************ updateFirstName() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ updateFirstName() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ updateFirstName() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        if (firstName.equals(userById.get().getFirstName())) {
            LOG.debug("************ updateFirstName() ---> " +
                    "The value cannot be updated to the same value. Argument firstName is equals to user.firstName.");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument firstName is equals to user.firstName.");
        }

        userRepository.updateFirstName(id, firstName);

        UserEntity updatedUser = userRepository.findById(id).get();
        LOG.debug("************ updateFirstName() ---> updatedUserFromRepository = " + updatedUser);

        userResult = UserConverter.mapEntityToDTO(updatedUser);
        LOG.debug("************ updateFirstName() ---> userResult = " + userResult);

        return userResult;
    }

    @Transactional
    @Override
    public UserDTO updateLastName(Long id, String lastName) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ updateLastName() ---> id = " + id + " ---> lastName = " + lastName);

        if ((id <= 0) || (lastName == null) || (deleteWhitespace(lastName).isEmpty())) {
            LOG.debug("************ updateLastName() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ updateLastName() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ updateLastName() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        if (!lastName.equals(userById.get().getLastName())) {
            LOG.debug("************ updateLastName() ---> " +
                    "The value cannot be updated to the same value. Argument lastName is equals to user.lastName.");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument lastName is equals to user.lastName.");
        }

        userRepository.updateLastName(id, lastName);

        UserEntity updatedUser = userRepository.findById(id).get();
        LOG.debug("************ updateLastName() ---> updatedUserFromRepository = " + updatedUser);

        userResult = UserConverter.mapEntityToDTO(updatedUser);
        LOG.debug("************ updateLastName() ---> userResult = " + userResult);

        return userResult;
    }

    @Transactional
    @Override
    public UserDTO updateEmail(Long id, String email) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ updateEmail() ---> id = " + id + " ---> email = " + email);

        if ((id <= 0) || (email == null) || (deleteWhitespace(email).isEmpty())) {
            LOG.debug("************ updateEmail() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ updateEmail() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ updateEmail() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        if (email.equals(userById.get().getEmail())) {
            LOG.debug("************ updateEmail() ---> " +
                    "The value cannot be updated to the same value. Argument email is equals to user.email.");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument email is equals to user.email.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            LOG.debug("************ updateEmail() ---> User with this email = '" + email
                    + "' exist. It is not allowed to register multiple users with the same email.");
            throw new UserWithEmailExistException("User with this email = '" + email
                    + "' exist. It is not allowed to register multiple users with the same email.");
        }

        userRepository.updateEmail(id, email);

        UserEntity updatedUser = userRepository.findById(id).get();

        LOG.debug("************ updateEmail() ---> updatedUserFromRepository = " + updatedUser);

        userResult = UserConverter.mapEntityToDTO(updatedUser);

        LOG.debug("************ updateEmail() ---> userResult = " + userResult);

        return userResult;
    }

    @Transactional
    @Override
    public UserDTO updateBirthday(Long id, LocalDate birthday) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ updateBirthday() ---> id = " + id + " ---> birthday = " + birthday);

        if ((id <= 0) || (birthday == null)) {
            LOG.debug("************ updateBirthday() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ updateBirthday() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ updateBirthday() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        if (birthday.equals(userById.get().getBirthday())) {
            LOG.debug("************ updateBirthday() ---> " +
                    "Value cannot be updated because argument birthday is equals to user.birthday.");
            throw new TheSameValueException(
                    "Value cannot be updated because argument birthday is equals to user.birthday.");
        }

        userRepository.updateBirthday(id, birthday);

        UserEntity updatedUser = userRepository.findById(id).get();

        LOG.debug("************ updateBirthday() ---> updatedUserFromRepository = " + updatedUser);

        userResult = UserConverter.mapEntityToDTO(updatedUser);

        LOG.debug("************ updateBirthday() ---> userResult = " + userResult);

        return userResult;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) throws UserWithValueNotExistException, InvalidArgumentException {
        boolean isDeletedUser = false;

        LOG.debug("************ delete() ---> userId = " + id);

        if (id <= 0) {
            LOG.debug("************ delete() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ delete() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ delete() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        userRepository.deleteById(id);
        // TODO: Think about this. Is it worth checking that the user is deleted correctly?
        isDeletedUser = true;

        LOG.debug("************ delete() ---> isDeletedUser = " + isDeletedUser);

        return isDeletedUser;
    }

    @Override
    public UserDTO findById(Long id) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ findById() ---> id = " + id);

        if (id <= 0) {
            LOG.debug("************ findById() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userById = userRepository.findById(id);
        LOG.debug("************ findById() ---> userFromRepositoryById = " + userById);

        if (userById.isEmpty()) {
            LOG.debug("************ findById() ---> User with this id = '" + id + "' not exist.");
            throw new UserWithValueNotExistException("User with this id = '" + id + "' not exist.");
        }

        userResult = UserConverter.mapEntityToDTO(userById.get());
        LOG.debug("************ findById() ---> userResult = " + userResult);

        return userResult;
    }

    @Override
    public List<UserDTO> findByFirstName(String firstName) throws Exception {
        List<UserDTO> userListResult = null;

        LOG.debug("************ findByFirstName() ---> firstName = " + firstName);

        if ((firstName == null) || (deleteWhitespace(firstName).isEmpty())) {
            LOG.debug("************ findByFirstName() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<UserEntity> userListByFirstName = userRepository.findByFirstName(firstName);
        LOG.debug("************ findByFirstName() ---> userListFromRepositoryByFirstName = " + userListByFirstName);

        if (userListByFirstName.isEmpty()) {
            LOG.debug("************ findByFirstName() ---> Users with this firstName = '" + firstName + "' not exist.");
            throw new UserWithValueNotExistException("Users with this firstName = '" + firstName + "' not exist.");
        }

        userListResult = UserConverter.mapListEntityToListDTO(userListByFirstName);
        LOG.debug("************ findByFirstName() ---> userListResult = " + userListResult);

        return userListResult;
    }

    @Override
    public List<UserDTO> findByLastName(String lastName) throws Exception {
        List<UserDTO> userListResult = null;

        LOG.debug("************ findByLastName() ---> lastName = " + lastName);

        if ((lastName == null) || (deleteWhitespace(lastName).isEmpty())) {
            LOG.debug("************ findByLastName() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<UserEntity> userListByLastName = userRepository.findByLastName(lastName);
        LOG.debug("************ findByLastName() ---> userListFromRepositoryByLastName = " + userListByLastName);

        if (userListByLastName.isEmpty()) {
            LOG.debug("************ findByLastName() ---> Users with this lastName = '" + lastName + "' not exist.");
            throw new UserWithValueNotExistException("Users with this lastName = '" + lastName + "' not exist.");
        }

        userListResult = UserConverter.mapListEntityToListDTO(userListByLastName);
        LOG.debug("************ findByLastName() ---> userListResult = " + userListResult);

        return userListResult;
    }

    @Override
    public List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) throws Exception {
        List<UserDTO> userListResult = null;

        LOG.debug("************ findByFirstNameAndLastName() ---> firstName = " + firstName + " ---> lastName = " + lastName);

        if ((firstName == null) || (deleteWhitespace(firstName).isEmpty())
                || (lastName == null) || (deleteWhitespace(lastName).isEmpty())) {
            LOG.debug("************ findByFirstNameAndLastName() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<UserEntity> userListByFirstNameAndLastName = userRepository.findByFirstNameAndLastName(firstName, lastName);
        LOG.debug("************ findByFirstNameAndLastName() ---> " +
                "userListFromRepositoryByFirstNameAndLastName = " + userListByFirstNameAndLastName);

        if (userListByFirstNameAndLastName.isEmpty()) {
            LOG.debug("************ findByFirstNameAndLastName() ---> " + "Users with this firstName = '" + firstName +
                    "' and lastName = '" + lastName + "' not exist.");
            throw new UserWithValueNotExistException("Users with this firstName = '" + firstName +
                    "' and lastName = '" + lastName + "' not exist.");
        }

        userListResult = UserConverter.mapListEntityToListDTO(userListByFirstNameAndLastName);
        LOG.debug("************ findByFirstNameAndLastName() ---> userListResult = " + userListResult);

        return userListResult;
    }

    @Override
    public UserDTO findByEmail(String email) throws Exception {
        UserDTO userResult = null;

        LOG.debug("************ findByEmail() ---> email = " + email);

        if ((email == null) || (deleteWhitespace(email).isEmpty())) {
            LOG.debug("************ findByEmail() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<UserEntity> userByEmail = userRepository.findByEmail(email);
        LOG.debug("************ findByEmail() ---> userFromRepositoryByEmail = " + userByEmail);

        if (userByEmail.isEmpty()) {
            LOG.debug("************ findByEmail() ---> Users with this email = '" + email + "' not exist.");
            throw new UserWithValueNotExistException("Users with this email = '" + email + "' not exist.");
        }

        userResult = UserConverter.mapEntityToDTO(userByEmail.get());
        LOG.debug("************ findByEmail() ---> userResult = " + userResult);

        return userResult;
    }

    @Override
    public List<UserDTO> findByBirthday(LocalDate birthday) throws Exception {
        List<UserDTO> userListResult = null;

        LOG.debug("************ findByBirthday() ---> birthday = " + birthday);

        if (birthday == null) {
            LOG.debug("************ findByBirthday() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<UserEntity> userListByBirthday = userRepository.findByBirthday(birthday);
        LOG.debug("************ findByBirthday() ---> userListFromRepositoryByBirthday = " + userListByBirthday);

        if (userListByBirthday.isEmpty()) {
            LOG.debug("************ findByBirthday() ---> Users with this birthday = '" + birthday + "' not exist.");
            throw new UserWithValueNotExistException("Users with this birthday = '" + birthday + "' not exist.");
        }

        userListResult = UserConverter.mapListEntityToListDTO(userListByBirthday);
        LOG.debug("************ findByBirthday() ---> userListResult = " + userListResult);

        return userListResult;
    }

    @Override
    public List<UserDTO> findAll() throws Exception{
        List<UserDTO> userListResult = null;

        List<UserEntity> allUsers = userRepository.findAll();
        LOG.debug("************ findAll() ---> allUsersFromRepository = " + allUsers);

        if (allUsers.isEmpty()) {
            LOG.debug("************ findAll() ---> Repository has no saved users.");
            throw new NotFoundObjectsException("Repository has no saved users.");
        }

        userListResult = UserConverter.mapListEntityToListDTO(allUsers);
        LOG.debug("************ findByBirthday() ---> userListResult = " + userListResult);

        return userListResult;
    }
}
