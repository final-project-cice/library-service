package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberAuthorDTO;
import com.trl.libraryservice.repository.entity.PhoneNumberAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class PhoneNumberAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberAuthorConverter.class);

    private PhoneNumberAuthorConverter() { }

    /**
     * @param entity
     * @return
     */
    public static PhoneNumberAuthorDTO mapEntityToDTO(PhoneNumberAuthorEntity entity) {
        PhoneNumberAuthorDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> phoneNumberAuthorEntity = " + entity
                + " ---> phoneNumberAuthorEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new PhoneNumberAuthorDTO();
            result.setId(entity.getId());
            result.setPhoneNumber(entity.getPhoneNumber());
            result.setCountryCode(entity.getCountryCode());
            result.setType(entity.getType());
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
    public static List<PhoneNumberAuthorDTO> mapListEntityToListDTO(List<PhoneNumberAuthorEntity> entities) {
        List<PhoneNumberAuthorDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberAuthorEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(PhoneNumberAuthorConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static PhoneNumberAuthorEntity mapDTOToEntity(PhoneNumberAuthorDTO dto) {
        PhoneNumberAuthorEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> phoneNumberAuthorDTO = " + dto
                + " ---> phoneNumberAuthorDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new PhoneNumberAuthorEntity();
            result.setId(dto.getId());
            result.setPhoneNumber(dto.getPhoneNumber());
            result.setCountryCode(dto.getCountryCode());
            result.setType(dto.getType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setAuthorEntity(AuthorConverter.mapDTOToEntity(dto.getAuthorDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<PhoneNumberAuthorEntity> mapListDTOToListEntity(List<PhoneNumberAuthorDTO> dtos) {
        List<PhoneNumberAuthorEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberAuthorDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(PhoneNumberAuthorConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
