package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
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

    private BookConverter() {
    }

    /**
     * This method is designed to convert BookEntity to BookDTO.
     *
     * @param entity That be converted to BookDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type BookDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static BookDTO mapEntityToDTO(BookEntity entity) throws InvalidArgumentException {
        BookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> bookEntity = " + entity
                + " ---> bookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new BookDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setGenres(GenreBookConverter.mapListEntityToListDTO(entity.getGenres()));
        result.setPublishingHouse(PublishingHouseConverter.mapEntityToDTO(entity.getPublishingHouse()));
        result.setPublicationDate(entity.getPublicationDate());
        result.setPathFile(entity.getPathFile());
        result.setComments(CommentBookConverter.mapListEntityToListDTO(entity.getComments()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert Set of BookEntity to Set of BookDTO.
     *
     * @param entities That be converted to Set of BookDTO. Parameter 'entities' must not be equal to null.
     * @return An Set of BookDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static Set<BookDTO> mapSetEntityToSetDTO(Set<BookEntity> entities) throws InvalidArgumentException {
        Set<BookDTO> resultSet = new HashSet<>();

        if (entities == null) {
            LOG.debug("************ mapSetEntityToSetDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
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
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static BookEntity mapDTOToEntity(BookDTO dto) throws InvalidArgumentException {
        BookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> bookDTO = " + dto
                + " ---> bookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new BookEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setGenres(GenreBookConverter.mapListDTOToListEntity(dto.getGenres()));
        result.setPublishingHouse(PublishingHouseConverter.mapDTOToEntity(dto.getPublishingHouse()));
        result.setPublicationDate(dto.getPublicationDate());
        result.setPathFile(dto.getPathFile());
        result.setComments(CommentBookConverter.mapListDTOToListEntity(dto.getComments()));

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert Set of BookDTO to Set of BookEntity.
     *
     * @param dtos That be converted to Set of BookEntity. Parameter 'dtos' must not be equal to null.
     * @return An Set of BookEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static Set<BookEntity> mapSetDTOToSetEntity(Set<BookDTO> dtos) throws InvalidArgumentException {
        Set<BookEntity> resultSet = new HashSet<>();

        if (dtos == null) {
            LOG.debug("************ mapSetDTOToSetEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> userDTOSet = " + dtos);

        for (BookDTO dto : dtos) {
            resultSet.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}