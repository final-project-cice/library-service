package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreAuthorDTO;
import com.trl.libraryservice.repository.entity.GenreAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class GenreAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreAuthorConverter.class);

    private GenreAuthorConverter() { }

    /**
     * @param entity
     * @return
     */
    public static GenreAuthorDTO mapEntityToDTO(GenreAuthorEntity entity) {
        GenreAuthorDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> genreAuthorEntity = " + entity
                + " ---> genreAuthorEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new GenreAuthorDTO();
            result.setId(entity.getId());
            result.setName(entity.getName());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setAuthorDTO(AuthorConverter.mapEntityToDTO(entity.getAuthorEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<GenreAuthorDTO> mapListEntityToListDTO(List<GenreAuthorEntity> entities) {
        List<GenreAuthorDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> genreAuthorEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(GenreAuthorConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static GenreAuthorEntity mapDTOToEntity(GenreAuthorDTO dto) {
        GenreAuthorEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> genreAuthorDTO = " + dto
                + " ---> genreAuthorDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new GenreAuthorEntity();
            result.setId(dto.getId());
            result.setName(dto.getName());
            // TODO: Check it, a cyclic call will be triggered here.
            result.setAuthorEntity(AuthorConverter.mapDTOToEntity(dto.getAuthorDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<GenreAuthorEntity> mapListDTOToListEntity(List<GenreAuthorDTO> dtos) {
        List<GenreAuthorEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> genreAuthorDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(GenreAuthorConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
