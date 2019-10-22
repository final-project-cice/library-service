package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.repository.entity.AuthorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

public final class AuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorConverter.class);

    private AuthorConverter() { }

    /**
     * @param entity
     * @return
     */
    public static AuthorDTO mapEntityToDTO(AuthorEntity entity) {
        AuthorDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> authorEntity = " + entity
                + " ---> authorEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
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
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookDTOS(BookConverter.mapSetEntityToSetDTO(entity.getBookEntities()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static Set<AuthorDTO> mapSetEntityToSetDTO(Set<AuthorEntity> entities) {
        Set<AuthorDTO> resultSet = null;

        LOG.debug("************ mapSetEntityToSetDTO() ---> authorEntitySet = " + entities);

        if (entities != null) {
            resultSet = entities.parallelStream()
                    .map(AuthorConverter::mapEntityToDTO)
                    .collect(Collectors.toSet());
        }

        LOG.debug("************ mapSetEntityToSetDTO() ---> resultSet = " + resultSet);

        return resultSet;
    }

    /**
     * @param dto
     * @return
     */
    public static AuthorEntity mapDTOToEntity(AuthorDTO dto) {
        AuthorEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> authorDTO = " + dto
                + " ---> authorDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
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
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookEntities(BookConverter.mapSetDTOToSetEntity(dto.getBookDTOS()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static Set<AuthorEntity> mapSetDTOToSetEntity(Set<AuthorDTO> dtos) {
        Set<AuthorEntity> resultSet = null;

        LOG.debug("************ mapSetDTOToSetEntity() ---> authorDTOSet = " + dtos);

        if (dtos != null) {
            resultSet = dtos.stream()
                    .map(AuthorConverter::mapDTOToEntity)
                    .collect(Collectors.toSet());
        }

        LOG.debug("************ mapSetDTOToSetEntity() ---> resultSet = " + resultSet);

        return resultSet;
    }
}
