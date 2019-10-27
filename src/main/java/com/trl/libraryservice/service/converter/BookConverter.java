package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.repository.entity.BookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public final class BookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BookConverter.class);

    private BookConverter() { }

    public static BookDTO mapEntityToDTO(BookEntity entity) {
        BookDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> bookEntity = " + entity
                + " ---> bookEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new BookDTO();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setGenres(GenreBookConverter.mapListEntityToListDTO(entity.getGenres()));
            result.setPublishingHouse(PublishingHouseConverter.mapEntityToDTO(entity.getPublishingHouse()));
            result.setPublicationDate(entity.getPublicationDate());
            result.setPathFile(entity.getPathFile());
            result.setComments(CommentBookConverter.mapListEntityToListDTO(entity.getComments()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static Set<BookDTO> mapSetEntityToSetDTO(Set<BookEntity> entities) {
        Set<BookDTO> resultSet = null;

        LOG.debug("************ mapSetEntityToSetDTO() ---> bookEntitySet = " + entities);

        if (entities != null) {
            resultSet = entities.parallelStream()
                    .map(BookConverter::mapEntityToDTO)
                    .collect(Collectors.toSet());
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param dto
     * @return
     */
    public static BookEntity mapDTOToEntity(BookDTO dto) {
        BookEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> bookDTO = " + dto
                + " ---> bookDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new BookEntity();
            result.setId(dto.getId());
            result.setName(dto.getName());
            result.setGenres(GenreBookConverter.mapListDTOToListEntity(dto.getGenres()));
            result.setPublishingHouse(PublishingHouseConverter.mapDTOToEntity(dto.getPublishingHouse()));
            result.setPublicationDate(dto.getPublicationDate());
            result.setPathFile(dto.getPathFile());
            result.setComments(CommentBookConverter.mapListDTOToListEntity(dto.getComments()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static Set<BookEntity> mapSetDTOToSetEntity(Set<BookDTO> dtos) {
        Set<BookEntity> resultSet = null;

        LOG.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + dtos);

        if (dtos != null) {
            resultSet = dtos.stream()
                    .map(BookConverter::mapDTOToEntity)
                    .collect(Collectors.toSet());
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}
