package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.repository.entity.BookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is designed to convert BookEntity to BookDTO and vice versa.
 * And also, this class is designed to convert Set of BookEntity to Set BookDTO and vice versa.
 */
public final class BookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(BookConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private BookConverter() {
    }

    /**
     * This method is designed to convert BookEntity to BookDTO.
     *
     * @param entity That be converted to BookDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type BookDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static BookDTO mapEntityToDTO(BookEntity entity) throws IllegalMethodParameterException {
        BookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert Set of BookEntity to Set of BookDTO.
     *
     * @param entities That be converted to Set of BookDTO. Parameter 'entities' must not be equal to null.
     * @return An Set of BookDTO.
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static Set<BookDTO> mapSetEntityToSetDTO(Set<BookEntity> entities) throws IllegalMethodParameterException {
        Set<BookDTO> resultSet = new HashSet<>();

        if (entities == null) {
            LOG.debug("************ mapSetEntityToSetDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> bookEntitySet = " + entities);

        for (BookEntity entity : entities) {
            resultSet.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * This method is designed to convert BookDTO to BookEntity.
     *
     * @param dto That be converted to BookEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type BookEntity.
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static BookEntity mapDTOToEntity(BookDTO dto) throws IllegalMethodParameterException {
        BookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert Set of BookDTO to Set of BookEntity.
     *
     * @param dtos That be converted to Set of BookEntity. Parameter 'dtos' must not be equal to null.
     * @return An Set of BookEntity.
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static Set<BookEntity> mapSetDTOToSetEntity(Set<BookDTO> dtos) throws IllegalMethodParameterException {
        Set<BookEntity> resultSet = new HashSet<>();

        if (dtos == null) {
            LOG.debug("************ mapSetDTOToSetEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + dtos);

        for (BookDTO dto : dtos) {
            resultSet.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}