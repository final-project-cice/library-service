package com.trl.libraryservice.service.impl;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.*;
import com.trl.libraryservice.repository.SubCommentCommentRepository;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;
import com.trl.libraryservice.service.SubCommentCommentService;
import com.trl.libraryservice.service.converter.UserConverter;

import static com.trl.libraryservice.service.converter.SubCommentCommentConverter.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubCommentCommentServiceImpl implements SubCommentCommentService {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentServiceImpl.class);

    private final SubCommentCommentRepository subCommentRepository;

    public SubCommentCommentServiceImpl(SubCommentCommentRepository subCommentRepository) {
        this.subCommentRepository = subCommentRepository;
    }

    @Override
    public SubCommentCommentDTO create(SubCommentCommentDTO subComment)
            throws InvalidArgumentException, InvalidObjectVariableValueException {
        SubCommentCommentDTO subCommentResult = null;

        LOG.debug("************ create() ---> subComment = " + subComment);

        if (subComment == null) {
            LOG.debug("************ create() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        // TODO: This operation is very resource-intensive. I'm not sure whether to do it.
        if ((subComment.getId() <= 0)
                || (subComment.getUser() == null)
                || (subComment.getText() == null) || (deleteWhitespace(subComment.getText()).isEmpty())
                || (subComment.getDate() == null)) {
            LOG.debug("************ create() ---> " +
                    "One of the variable from parameter 'subComment' is incorrect, check the variables that it has the 'subComment'.");
            throw new InvalidObjectVariableValueException(
                    "One of the variable from parameter 'subComment' is incorrect, check the variables that it has the 'subComment'.");
        }

        SubCommentCommentEntity savedSubComment = subCommentRepository.save(mapDTOToEntity(subComment));

        LOG.debug("************ create() ---> savedSubComment = " + savedSubComment);

        subCommentResult = mapEntityToDTO(savedSubComment);

        LOG.debug("************ create() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateUser(Long id, UserDTO user)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException {
        SubCommentCommentDTO subCommentResult = null;

        LOG.debug("************ updateUser() ---> id = " + id + " ---> user = " + user);

        if ((id <= 0) || (user == null)) {
            LOG.debug("************ updateUser() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        // TODO: Think about this. Check all user fields for validity or do not check?

        Optional<SubCommentCommentEntity> subCommentById = subCommentRepository.findById(id);
        LOG.debug("************ updateUser() ---> subCommentFromRepositoryById = " + subCommentById);

        if (subCommentById.isEmpty()) {
            LOG.debug("************ updateUser() ---> SubComment with this id = '" + id + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this id = '" + id + "' not exist.");
        }

        if (user.equals(UserConverter.mapEntityToDTO(subCommentById.get().getUser()))) {
            LOG.debug("************ updateUser() ---> " +
                    "The value cannot be updated to the same value. Argument user is equals to subComment.getUser().");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument user is equals to subComment.getUser().");
        }

        subCommentRepository.updateUser(id, UserConverter.mapDTOToEntity(user));

        SubCommentCommentEntity updatedSubComment = subCommentRepository.findById(id).get();
        LOG.debug("************ updateUser() ---> updatedSubCommentFromRepository = " + updatedSubComment);

        subCommentResult = mapEntityToDTO(updatedSubComment);
        LOG.debug("************ updateUser() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateText(Long id, String text)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException {
        SubCommentCommentDTO subCommentResult = null;

        LOG.debug("************ updateText() ---> id = " + id + " ---> text = " + text);

        if ((id <= 0) || (text == null) || (deleteWhitespace(text).isEmpty())) {
            LOG.debug("************ updateText() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<SubCommentCommentEntity> subCommentById = subCommentRepository.findById(id);
        LOG.debug("************ updateText() ---> subCommentFromRepositoryById = " + subCommentById);

        if (subCommentById.isEmpty()) {
            LOG.debug("************ updateText() ---> SubComment with this id = '" + id + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this id = '" + id + "' not exist.");
        }

        if (text.equals(subCommentById.get().getText())) {
            LOG.debug("************ updateText() ---> " +
                    "The value cannot be updated to the same value. Argument text is equals to subComment.getText().");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument text is equals to subComment.getText().");
        }

        subCommentRepository.updateText(id, text);

        SubCommentCommentEntity updatedSubComment = subCommentRepository.findById(id).get();
        LOG.debug("************ updateText() ---> updatedSubCommentFromRepository = " + updatedSubComment);

        subCommentResult = mapEntityToDTO(updatedSubComment);
        LOG.debug("************ updateText() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    @Transactional
    @Override
    public SubCommentCommentDTO updateDate(Long id, LocalDate date)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException, TheSameValueException {
        SubCommentCommentDTO subCommentResult = null;

        LOG.debug("************ updateDate() ---> id = " + id + " ---> date = " + date);

        if ((id <= 0) || (date == null)) {
            LOG.debug("************ updateDate() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<SubCommentCommentEntity> subCommentById = subCommentRepository.findById(id);
        LOG.debug("************ updateDate() ---> subCommentFromRepositoryById = " + subCommentById);

        if (subCommentById.isEmpty()) {
            LOG.debug("************ updateDate() ---> SubComment with this id = '" + id + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this id = '" + id + "' not exist.");
        }

        if (date.equals(subCommentById.get().getDate())) {
            LOG.debug("************ updateDate() ---> " +
                    "The value cannot be updated to the same value. Argument date is equals to subComment.getDate().");
            throw new TheSameValueException(
                    "The value cannot be updated to the same value. Argument date is equals to subComment.getDate().");
        }

        subCommentRepository.updateDate(id, date);

        SubCommentCommentEntity updatedSubComment = subCommentRepository.findById(id).get();
        LOG.debug("************ updateDate() ---> updatedSubCommentFromRepository = " + updatedSubComment);

        subCommentResult = mapEntityToDTO(updatedSubComment);
        LOG.debug("************ updateDate() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) throws InvalidArgumentException, EntityNotFoundWithThisValueException {
        boolean isDeletedSubComment = false;

        LOG.debug("************ delete() ---> id = " + id);

        if (id <= 0) {
            LOG.debug("************ delete() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<SubCommentCommentEntity> subCommentById = subCommentRepository.findById(id);
        LOG.debug("************ delete() ---> subCommentFromRepositoryById = " + subCommentById);

        if (subCommentById.isEmpty()) {
            LOG.debug("************ delete() ---> SubComment with this id = '" + id + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this id = '" + id + "' not exist.");
        }

        subCommentRepository.deleteById(id);
        // TODO: Think about this. Is it worth checking that the user is deleted correctly?
        isDeletedSubComment = true;

        LOG.debug("************ delete() ---> isDeletedSubComment = " + isDeletedSubComment);

        return isDeletedSubComment;
    }

    @Override
    public SubCommentCommentDTO findById(Long id) throws InvalidArgumentException, EntityNotFoundWithThisValueException {
        SubCommentCommentDTO subCommentResult = null;

        LOG.debug("************ findById() ---> id = " + id);

        if (id <= 0) {
            LOG.debug("************ findById() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        Optional<SubCommentCommentEntity> subCommentById = subCommentRepository.findById(id);
        LOG.debug("************ findById() ---> subCommentFromRepositoryById = " + subCommentById);

        if (subCommentById.isEmpty()) {
            LOG.debug("************ findById() ---> SubComment with this id = '" + id + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this id = '" + id + "' not exist.");
        }

        subCommentResult = mapEntityToDTO(subCommentById.get());
        LOG.debug("************ findById() ---> subCommentResult = " + subCommentResult);

        return subCommentResult;
    }

    /**
     * This method is very resource-intensive.
     * Maybe it should be done differently.
     * Carefully with this method.
     */
    @Override
    public List<SubCommentCommentDTO> findByUser(UserDTO user)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException {
        List<SubCommentCommentDTO> subCommentListResult = null;

        LOG.debug("************ findByUser() ---> user = " + user);

        if (user == null) {
            LOG.debug("************ findByUser() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        // TODO: Think about this. Check all user fields for validity or do not check?

        List<SubCommentCommentEntity> subCommentListByUser = subCommentRepository.findByUser(UserConverter.mapDTOToEntity(user));
        LOG.debug("************ findByUser() ---> subCommentListFromRepositoryByUser = " + subCommentListByUser);

        if (subCommentListByUser.isEmpty()) {
            LOG.debug("************ findByUser() ---> SubComment with this user = '" + user + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this user = '" + user + "' not exist.");
        }

        subCommentListResult = mapListEntityToListDTO(subCommentListByUser);
        LOG.debug("************ findByUser() ---> subCommentListResult = " + subCommentListResult);

        return subCommentListResult;
    }

    /**
     * This method is very resource-intensive.
     * Maybe it should be done differently.
     * Carefully with this method.
     */
    @Override
    public List<SubCommentCommentDTO> findByText(String text)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException {
        List<SubCommentCommentDTO> subCommentListResult = null;

        LOG.debug("************ findByText() ---> text = " + text);

        if (text == null || (deleteWhitespace(text).isEmpty())) {
            LOG.debug("************ findByText() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<SubCommentCommentEntity> subCommentListByText = subCommentRepository.findByText(text);
        LOG.debug("************ findByText() ---> subCommentListFromRepositoryByText = " + subCommentListByText);

        if (subCommentListByText.isEmpty()) {
            LOG.debug("************ findByText() ---> SubComment with this 'text' = '" + text + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this 'text' = '" + text + "' not exist.");
        }

        subCommentListResult = mapListEntityToListDTO(subCommentListByText);
        LOG.debug("************ findByText() ---> subCommentListResult = " + subCommentListResult);

        return subCommentListResult;
    }

    /**
     * This method is very resource-intensive.
     * Maybe it should be done differently.
     * Carefully with this method.
     */
    @Override
    public List<SubCommentCommentDTO> findByDate(LocalDate date)
            throws InvalidArgumentException, EntityNotFoundWithThisValueException {
        List<SubCommentCommentDTO> subCommentListResult = null;

        LOG.debug("************ findByDate() ---> date = " + date);

        if (date == null) {
            LOG.debug("************ findByDate() ---> " +
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        List<SubCommentCommentEntity> subCommentListByDate = subCommentRepository.findByDate(date);
        LOG.debug("************ findByDate() ---> subCommentListFromRepositoryByDate = " + subCommentListByDate);

        if (subCommentListByDate.isEmpty()) {
            LOG.debug("************ findByDate() ---> SubComment with this 'date' = '" + date + "' not exist.");
            throw new EntityNotFoundWithThisValueException("SubComment with this 'date' = '" + date + "' not exist.");
        }

        subCommentListResult = mapListEntityToListDTO(subCommentListByDate);
        LOG.debug("************ findByDate() ---> subCommentListResult = " + subCommentListResult);

        return subCommentListResult;
    }

    /**
     * This method is very resource-intensive.
     * Maybe it should be done differently.
     * Carefully with this method.
     */
    @Override
    public List<SubCommentCommentDTO> findAll() throws EntitiesNotFoundException {
        List<SubCommentCommentDTO> subCommentListResult = null;

        List<SubCommentCommentEntity> allSubComments = subCommentRepository.findAll();
        LOG.debug("************ findAll() ---> allSubCommentFromRepository = " + allSubComments);

        if (allSubComments.isEmpty()) {
            LOG.debug("************ findAll() ---> Repository has no saved subComments.");
            throw new EntitiesNotFoundException("Repository has no saved subComments.");
        }

        subCommentListResult = mapListEntityToListDTO(allSubComments);
        LOG.debug("************ findByDate() ---> subCommentListResult = " + subCommentListResult);

        return subCommentListResult;
    }
}
