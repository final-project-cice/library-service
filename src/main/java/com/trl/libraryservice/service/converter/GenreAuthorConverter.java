package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreAuthorDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
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
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private GenreAuthorConverter() {
    }

    /**
     * This method is designed to convert GenreAuthorEntity to GenreAuthorDTO.
     *
     * @param entity That be converted to GenreAuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type GenreAuthorDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static GenreAuthorDTO mapEntityToDTO(GenreAuthorEntity entity) throws IllegalMethodParameterException {
        GenreAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static List<GenreAuthorDTO> mapListEntityToListDTO(List<GenreAuthorEntity> entities) throws IllegalMethodParameterException {
        List<GenreAuthorDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static GenreAuthorEntity mapDTOToEntity(GenreAuthorDTO dto) throws IllegalMethodParameterException {
        GenreAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static List<GenreAuthorEntity> mapListDTOToListEntity(List<GenreAuthorDTO> dtos) throws IllegalMethodParameterException {
        List<GenreAuthorEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> genreAuthorDTOList = " + dtos);

        for (GenreAuthorDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}