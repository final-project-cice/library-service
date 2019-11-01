package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.repository.entity.BookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal BookEntity} to {@literal BookDTO} and vice versa.
 * And also, this class is designed to convert {@literal Set<BookEntity>} to {@literal Set<BookDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class BookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BookConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private BookConverter() {
    }

    /**
     * Convert {@literal BookEntity} to {@literal BookDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal BookDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static BookDTO mapEntityToDTO(BookEntity entity) {
        BookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> bookEntity = " + entity
                + " ---> bookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new BookDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.addGenres(GenreBookConverter.mapListEntityToListDTO(entity.getGenres()));
        result.setPublishingHouse(PublishingHouseConverter.mapEntityToDTO(entity.getPublishingHouse()));
        result.setPublicationDate(entity.getPublicationDate());
        result.setPathFile(entity.getPathFile());
        result.addComments(CommentBookConverter.mapListEntityToListDTO(entity.getComments()));
        result.addAuthors(AuthorConverter.mapSetEntityToSetDTO(entity.getAuthors()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Set<BookEntity>} to {@literal Set<BookDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Set<BookDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Set<BookDTO> mapSetEntityToSetDTO(Set<BookEntity> entities) {
        Set<BookDTO> resultSet;

        if (entities == null) {
            LOG.debug("************ mapSetEntityToSetDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> bookEntitySet = " + entities);

        resultSet = entities.parallelStream()
                .map(BookConverter::mapEntityToDTO)
                .collect(Collectors.toSet());

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * Convert {@literal BookDTO} to {@literal BookEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal BookEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static BookEntity mapDTOToEntity(BookDTO dto) {
        BookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> bookDTO = " + dto
                + " ---> bookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new BookEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.addGenres(GenreBookConverter.mapListDTOToListEntity(dto.getGenres()));
        result.setPublishingHouse(PublishingHouseConverter.mapDTOToEntity(dto.getPublishingHouse()));
        result.setPublicationDate(dto.getPublicationDate());
        result.setPathFile(dto.getPathFile());
        result.addComments(CommentBookConverter.mapListDTOToListEntity(dto.getComments()));
        result.addAuthors(AuthorConverter.mapSetDTOToSetEntity(dto.getAuthors()));

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Set<BookDTO} to {@literal Set<BookEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal Set<BookEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static Set<BookEntity> mapSetDTOToSetEntity(Set<BookDTO> dtos) {
        Set<BookEntity> resultSet;

        if (dtos == null) {
            LOG.debug("************ mapSetDTOToSetEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + dtos);

        resultSet = dtos.parallelStream()
                .map(BookConverter::mapDTOToEntity)
                .collect(Collectors.toSet());

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}