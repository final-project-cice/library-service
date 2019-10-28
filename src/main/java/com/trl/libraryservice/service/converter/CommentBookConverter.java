package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
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

    private CommentBookConverter() {
    }

    /**
     * This method is designed to convert CommentBookEntity to CommentBookDTO.
     *
     * @param entity That be converted to CommentBookDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type CommentBookDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static CommentBookDTO mapEntityToDTO(CommentBookEntity entity) throws InvalidArgumentException {
        CommentBookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> commentBookEntity = " + entity
                + " ---> commentBookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new CommentBookDTO();
        result.setId(entity.getId());
        result.setUser(UserConverter.mapEntityToDTO(entity.getUser()));
        result.setText(entity.getText());
        result.setDate(entity.getDate());
        result.setSubComments(SubCommentCommentConverter.mapListEntityToListDTO(entity.getSubComments()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of CommentBookEntity to List of CommentBookDTO.
     *
     * @param entities That be converted to List of CommentBookDTO. Parameter 'entities' must not be equal to null.
     * @return An List of CommentBookDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<CommentBookDTO> mapListEntityToListDTO(List<CommentBookEntity> entities) throws InvalidArgumentException {
        List<CommentBookDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
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
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static CommentBookEntity mapDTOToEntity(CommentBookDTO dto) throws InvalidArgumentException {
        CommentBookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> commentBookDTO = " + dto
                + " ---> commentBookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new CommentBookEntity();
        result.setId(dto.getId());
        result.setUser(UserConverter.mapDTOToEntity(dto.getUser()));
        result.setText(dto.getText());
        result.setDate(dto.getDate());
        result.setSubComments(SubCommentCommentConverter.mapListDTOToListEntity(dto.getSubComments()));

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of CommentBookDTO to List of CommentBookEntity.
     *
     * @param dtos That be converted to List of CommentBookEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of CommentBookEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<CommentBookEntity> mapListDTOToListEntity(List<CommentBookDTO> dtos) throws InvalidArgumentException {
        List<CommentBookEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> commentBookDTOList = " + dtos);

        for (CommentBookDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}