package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.repository.entity.AuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal AuthorEntity} to {@literal AuthorDTO} and vice versa.
 * And also, this class is designed to convert {@literal Set<AuthorEntity>} to {@literal Set<AuthorDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class AuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private AuthorConverter() {
    }

    /**
     * Convert {@literal AuthorEntity} to {@literal AuthorDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal AuthorDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static AuthorDTO mapEntityToDTO(AuthorEntity entity) {
        AuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> authorEntity = " + entity
                + " ---> authorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AuthorDTO();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.addEmails(EmailAuthorConverter.mapListEntityToListDTO(entity.getEmails()));
        result.addPhoneNumbers(PhoneNumberAuthorConverter.mapListEntityToListDTO(entity.getPhoneNumbers()));
        result.addAddresses(AddressAuthorConverter.mapListEntityToListDTO(entity.getAddresses()));
        result.setBirthday(entity.getBirthday());
        result.addGenres(GenreAuthorConverter.mapListEntityToListDTO(entity.getGenres()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Set<AuthorEntity>} to {@literal Set<AuthorDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Set<AuthorDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Set<AuthorDTO> mapSetEntityToSetDTO(Set<AuthorEntity> entities) {
        Set<AuthorDTO> resultSet;

        if (entities == null) {
            LOG.debug("************ mapSetEntityToSetDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> authorEntitySet = " + entities);

        resultSet = entities.parallelStream()
                .map(AuthorConverter::mapEntityToDTO)
                .collect(Collectors.toSet());

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * Convert {@literal AuthorDTO} to {@literal AuthorEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal AuthorEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static AuthorEntity mapDTOToEntity(AuthorDTO dto) {
        AuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> authorDTO = " + dto
                + " ---> authorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AuthorEntity();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.addEmails(EmailAuthorConverter.mapListDTOToListEntity(dto.getEmails()));
        result.addPhoneNumbers(PhoneNumberAuthorConverter.mapListDTOToListEntity(dto.getPhoneNumbers()));
        result.addAddresses(AddressAuthorConverter.mapListDTOToListEntity(dto.getAddresses()));
        result.setBirthday(dto.getBirthday());
        result.addGenres(GenreAuthorConverter.mapListDTOToListEntity(dto.getGenres()));

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Set<AuthorDTO} to {@literal Set<AuthorEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal Set<AuthorEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static Set<AuthorEntity> mapSetDTOToSetEntity(Set<AuthorDTO> dtos) {
        Set<AuthorEntity> resultSet;

        if (dtos == null) {
            LOG.debug("************ mapSetDTOToSetEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> authorDTOSet = " + dtos);

        resultSet = dtos.parallelStream()
                .map(AuthorConverter::mapDTOToEntity)
                .collect(Collectors.toSet());

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}