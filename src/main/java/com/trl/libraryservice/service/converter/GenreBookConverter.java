package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreBookDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.GenreBookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert GenreBookEntity to GenreBookDTO and vice versa.
 * And also, this class is designed to convert List of GenreBookEntity to List GenreBookDTO and vice versa.
 */
public final class GenreBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreBookConverter.class);

    private GenreBookConverter() {
    }

    /**
     * This method is designed to convert GenreBookEntity to GenreBookDTO.
     *
     * @param entity That be converted to GenreBookDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type GenreBookDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static GenreBookDTO mapEntityToDTO(GenreBookEntity entity) throws InvalidArgumentException {
        GenreBookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> genreBookEntity = " + entity
                + " ---> genreBookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new GenreBookDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of GenreBookEntity to List of GenreBookDTO.
     *
     * @param entities That be converted to List of GenreBookDTO. Parameter 'entities' must not be equal to null.
     * @return An List of GenreBookDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<GenreBookDTO> mapListEntityToListDTO(List<GenreBookEntity> entities) throws InvalidArgumentException {
        List<GenreBookDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> genreBookEntityList = " + entities);

        for (GenreBookEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert GenreBookDTO to GenreBookEntity.
     *
     * @param dto That be converted to GenreBookEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type GenreBookEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static GenreBookEntity mapDTOToEntity(GenreBookDTO dto) throws InvalidArgumentException {
        GenreBookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> genreBookDTO = " + dto
                + " ---> genreBookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new GenreBookEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of GenreBookDTO to List of GenreBookEntity.
     *
     * @param dtos That be converted to List of GenreBookEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of GenreBookEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<GenreBookEntity> mapListDTOToListEntity(List<GenreBookDTO> dtos) throws InvalidArgumentException {
        List<GenreBookEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> genreBookDTOList = " + dtos);

        for (GenreBookDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}