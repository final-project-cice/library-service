package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreBookDTO;
import com.trl.libraryservice.repository.entity.GenreBookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal GenreBookEntity} to {@literal GenreBookDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<GenreBookEntity>} to {@literal List<GenreBookDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class GenreBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreBookConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private GenreBookConverter() {
    }

    /**
     * Convert {@literal GenreBookEntity} to {@literal GenreBookDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal GenreBookDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static GenreBookDTO mapEntityToDTO(GenreBookEntity entity) {
        GenreBookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<GenreBookEntity>} to {@literal List<GenreBookDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<GenreBookDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<GenreBookDTO> mapListEntityToListDTO(List<GenreBookEntity> entities) {
        List<GenreBookDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> genreBookEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(GenreBookConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal GenreBookDTO} to {@literal GenreBookEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal GenreBookEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static GenreBookEntity mapDTOToEntity(GenreBookDTO dto) {
        GenreBookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<GenreBookDTO} to {@literal List<GenreBookEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<GenreBookEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<GenreBookEntity> mapListDTOToListEntity(List<GenreBookDTO> dtos) {
        List<GenreBookEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> genreBookDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(GenreBookConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}