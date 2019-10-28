package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreAuthorDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.GenreAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert GenreAuthorEntity to GenreAuthorDTO and vice versa.
 * And also, this class is designed to convert List of GenreAuthorEntity to List GenreAuthorDTO and vice versa.
 */
public final class GenreAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreAuthorConverter.class);

    private GenreAuthorConverter() {
    }

    /**
     * This method is designed to convert GenreAuthorEntity to GenreAuthorDTO.
     *
     * @param entity That be converted to GenreAuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type GenreAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static GenreAuthorDTO mapEntityToDTO(GenreAuthorEntity entity) throws InvalidArgumentException {
        GenreAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> genreAuthorEntity = " + entity
                + " ---> genreAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new GenreAuthorDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of GenreAuthorEntity to List of GenreAuthorDTO.
     *
     * @param entities That be converted to List of GenreAuthorDTO. Parameter 'entities' must not be equal to null.
     * @return An List of GenreAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<GenreAuthorDTO> mapListEntityToListDTO(List<GenreAuthorEntity> entities) throws InvalidArgumentException {
        List<GenreAuthorDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> genreAuthorEntityList = " + entities);

        for (GenreAuthorEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert GenreAuthorDTO to GenreAuthorEntity.
     *
     * @param dto That be converted to GenreAuthorEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type GenreAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static GenreAuthorEntity mapDTOToEntity(GenreAuthorDTO dto) throws InvalidArgumentException {
        GenreAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> genreAuthorDTO = " + dto
                + " ---> genreAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new GenreAuthorEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of GenreAuthorDTO to List of GenreAuthorEntity.
     *
     * @param dtos That be converted to List of GenreAuthorEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of GenreAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<GenreAuthorEntity> mapListDTOToListEntity(List<GenreAuthorDTO> dtos) throws InvalidArgumentException {
        List<GenreAuthorEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> genreAuthorDTOList = " + dtos);

        for (GenreAuthorDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}