package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert SubCommentCommentEntity to SubCommentCommentDTO and vice versa.
 * And also, this class is designed to convert List of SubCommentCommentEntity to List SubCommentCommentDTO and vice versa.
 */
public final class SubCommentCommentConverter {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentConverter.class);

    private SubCommentCommentConverter() {
    }

    /**
     * This method is designed to convert SubCommentCommentEntity to SubCommentCommentDTO.
     *
     * @param entity That be converted to SubCommentCommentDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type SubCommentCommentDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static SubCommentCommentDTO mapEntityToDTO(SubCommentCommentEntity entity) throws InvalidArgumentException {
        SubCommentCommentDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> subCommentCommentEntity = " + entity
                + " ---> subCommentCommentEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new SubCommentCommentDTO();
        result.setId(entity.getId());
        result.setUser(UserConverter.mapEntityToDTO(entity.getUser()));
        result.setText(entity.getText());
        result.setDate(entity.getDate());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of SubCommentCommentEntity to List of SubCommentCommentDTO.
     *
     * @param entities That be converted to List of SubCommentCommentDTO. Parameter 'entities' must not be equal to null.
     * @return An List of SubCommentCommentDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<SubCommentCommentDTO> mapListEntityToListDTO(List<SubCommentCommentEntity> entities) throws InvalidArgumentException {
        List<SubCommentCommentDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> subCommentCommentEntityList = " + entities);

        for (SubCommentCommentEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert SubCommentCommentDTO to SubCommentCommentEntity.
     *
     * @param dto That be converted to SubCommentCommentEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type SubCommentCommentEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static SubCommentCommentEntity mapDTOToEntity(SubCommentCommentDTO dto) throws InvalidArgumentException {
        SubCommentCommentEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> subCommentCommentDTO = " + dto
                + " ---> subCommentCommentDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new SubCommentCommentEntity();
        result.setId(dto.getId());
        result.setUser(UserConverter.mapDTOToEntity(dto.getUser()));
        result.setText(dto.getText());
        result.setDate(dto.getDate());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of SubCommentCommentDTO to List of SubCommentCommentEntity.
     *
     * @param dtos That be converted to List of SubCommentCommentEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of SubCommentCommentEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<SubCommentCommentEntity> mapListDTOToListEntity(List<SubCommentCommentDTO> dtos) throws InvalidArgumentException {
        List<SubCommentCommentEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> subCommentCommentDTOList = " + dtos);

        for (SubCommentCommentDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
