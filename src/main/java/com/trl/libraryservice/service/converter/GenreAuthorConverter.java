package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreAuthorDTO;
import com.trl.libraryservice.repository.entity.GenreAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal GenreAuthorEntity} to {@literal GenreAuthorDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<GenreAuthorEntity>} to {@literal List<GenreAuthorDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class GenreAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreAuthorConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private GenreAuthorConverter() {
    }

    /**
     * Convert {@literal GenreAuthorEntity} to {@literal GenreAuthorDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal GenreAuthorDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static GenreAuthorDTO mapEntityToDTO(GenreAuthorEntity entity) {
        GenreAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<GenreAuthorEntity>} to {@literal List<GenreAuthorDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<GenreAuthorDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<GenreAuthorDTO> mapListEntityToListDTO(List<GenreAuthorEntity> entities) {
        List<GenreAuthorDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> genreAuthorEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(GenreAuthorConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal GenreAuthorDTO} to {@literal GenreAuthorEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal GenreAuthorEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static GenreAuthorEntity mapDTOToEntity(GenreAuthorDTO dto) {
        GenreAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<GenreAuthorDTO} to {@literal List<GenreAuthorEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<GenreAuthorEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<GenreAuthorEntity> mapListDTOToListEntity(List<GenreAuthorDTO> dtos) {
        List<GenreAuthorEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> genreAuthorDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(GenreAuthorConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}