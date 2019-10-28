package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.AuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is designed to convert AuthorEntity to AuthorDTO and vice versa.
 * And also, this class is designed to convert Set of AuthorEntity to Set AuthorDTO and vice versa.
 */
public final class AuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorConverter.class);

    private AuthorConverter() {
    }

    /**
     * This method is designed to convert AuthorEntity to AuthorDTO.
     *
     * @param entity That be converted to AuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type AuthorDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static AuthorDTO mapEntityToDTO(AuthorEntity entity) throws InvalidArgumentException {
        AuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> authorEntity = " + entity
                + " ---> authorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AuthorDTO();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setEmails(
                EmailAuthorConverter.mapListEntityToListDTO(entity.getEmails())
        );
        result.setPhoneNumbers(
                PhoneNumberAuthorConverter.mapListEntityToListDTO(entity.getPhoneNumbers())
        );
        result.setAddresses(
                AddressAuthorConverter.mapListEntityToListDTO(entity.getAddresses())
        );
        result.setBirthday(entity.getBirthday());
        result.setGenres(
                GenreAuthorConverter.mapListEntityToListDTO(entity.getGenres())
        );

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert Set of AuthorEntity to Set of AuthorDTO.
     *
     * @param entities That be converted to Set of AuthorDTO. Parameter 'entities' must not be equal to null.
     * @return An Set of AuthorDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static Set<AuthorDTO> mapSetEntityToSetDTO(Set<AuthorEntity> entities) throws InvalidArgumentException {
        Set<AuthorDTO> resultSet = new HashSet<>();

        if (entities == null) {
            LOG.debug("************ mapSetEntityToSetDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> authorEntitySet = " + entities);

        for (AuthorEntity entity : entities) {
            resultSet.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * This method is designed to convert AuthorDTO to AuthorEntity.
     *
     * @param dto That be converted to AuthorEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type AuthorEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static AuthorEntity mapDTOToEntity(AuthorDTO dto) throws InvalidArgumentException {
        AuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> authorDTO = " + dto
                + " ---> authorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AuthorEntity();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setEmails(
                EmailAuthorConverter.mapListDTOToListEntity(dto.getEmails())
        );
        result.setPhoneNumbers(
                PhoneNumberAuthorConverter.mapListDTOToListEntity(dto.getPhoneNumbers())
        );
        result.setAddresses(
                AddressAuthorConverter.mapListDTOToListEntity(dto.getAddresses())
        );
        result.setBirthday(dto.getBirthday());
        result.setGenres(
                GenreAuthorConverter.mapListDTOToListEntity(dto.getGenres())
        );

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert Set of AuthorDTO to Set of AuthorEntity.
     *
     * @param dtos That be converted to Set of AuthorEntity. Parameter 'dtos' must not be equal to null.
     * @return An Set of AuthorEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static Set<AuthorEntity> mapSetDTOToSetEntity(Set<AuthorDTO> dtos) throws InvalidArgumentException {
        Set<AuthorEntity> resultSet = new HashSet<>();

        if (dtos == null) {
            LOG.debug("************ mapSetDTOToSetEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> authorDTOSet = " + dtos);

        for (AuthorDTO dto : dtos) {
            resultSet.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}