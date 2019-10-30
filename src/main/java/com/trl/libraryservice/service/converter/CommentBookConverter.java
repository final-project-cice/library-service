package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.repository.entity.CommentBookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert CommentBookEntity to CommentBookDTO and vice versa.
 * And also, this class is designed to convert List of CommentBookEntity to List CommentBookDTO and vice versa.
 */
public final class CommentBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private CommentBookConverter() {
    }

    /**
     * This method is designed to convert CommentBookEntity to CommentBookDTO.
     *
     * @param entity That be converted to CommentBookDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type CommentBookDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static CommentBookDTO mapEntityToDTO(CommentBookEntity entity) throws IllegalMethodParameterException {
        CommentBookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> commentBookEntity = " + entity
                + " ---> commentBookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new CommentBookDTO();
        result.setId(entity.getId());
        result.setUser(UserConverter.mapEntityToDTO(entity.getUser()));
        result.setText(entity.getText());
        result.setDate(entity.getDate());
        result.addSubComments(SubCommentCommentConverter.mapListEntityToListDTO(entity.getSubComments()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of CommentBookEntity to List of CommentBookDTO.
     *
     * @param entities That be converted to List of CommentBookDTO. Parameter 'entities' must not be equal to null.
     * @return An List of CommentBookDTO.
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static List<CommentBookDTO> mapListEntityToListDTO(List<CommentBookEntity> entities) throws IllegalMethodParameterException {
        List<CommentBookDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> commentBookEntityList = " + entities);

        for (CommentBookEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert CommentBookDTO to CommentBookEntity.
     *
     * @param dto That be converted to CommentBookEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type CommentBookEntity.
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static CommentBookEntity mapDTOToEntity(CommentBookDTO dto) throws IllegalMethodParameterException {
        CommentBookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> commentBookDTO = " + dto
                + " ---> commentBookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new CommentBookEntity();
        result.setId(dto.getId());
        result.setUser(UserConverter.mapDTOToEntity(dto.getUser()));
        result.setText(dto.getText());
        result.setDate(dto.getDate());
        result.addSubComments(SubCommentCommentConverter.mapListDTOToListEntity(dto.getSubComments()));

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of CommentBookDTO to List of CommentBookEntity.
     *
     * @param dtos That be converted to List of CommentBookEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of CommentBookEntity.
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static List<CommentBookEntity> mapListDTOToListEntity(List<CommentBookDTO> dtos) throws IllegalMethodParameterException {
        List<CommentBookEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> commentBookDTOList = " + dtos);

        for (CommentBookDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}