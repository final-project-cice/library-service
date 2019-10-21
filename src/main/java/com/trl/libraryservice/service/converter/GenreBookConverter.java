package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.GenreBookDTO;
import com.trl.libraryservice.repository.entity.GenreBookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class GenreBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(GenreBookConverter.class);

    private GenreBookConverter() { }

    public static GenreBookDTO mapEntityToDTO(GenreBookEntity entity) {
        GenreBookDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> genreBookEntity = " + entity
                + " ---> genreBookEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new GenreBookDTO();
            result.setId(entity.getId());
            result.setName(entity.getName());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookDTO(BookConverter.mapEntityToDTO(entity.getBookEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<GenreBookDTO> mapListEntityToListDTO(List<GenreBookEntity> entities) {
        List<GenreBookDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> genreBookEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(GenreBookConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static GenreBookEntity mapDTOToEntity(GenreBookDTO dto) {
        GenreBookEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> genreBookDTO = " + dto
                + " ---> genreBookDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new GenreBookEntity();
            result.setId(dto.getId());
            result.setName(dto.getName());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookEntity(BookConverter.mapDTOToEntity(dto.getBookDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<GenreBookEntity> mapListDTOToListEntity(List<GenreBookDTO> dtos) {
        List<GenreBookEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> genreBookDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(GenreBookConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
