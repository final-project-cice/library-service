package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.PhoneNumberPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class PhoneNumberPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberPublishingHouseConverter.class);

    private PhoneNumberPublishingHouseConverter() { }

    /**
     *
     * */
    public static PhoneNumberPublishingHouseDTO mapEntityToDTO(PhoneNumberPublishingHouseEntity entity) {
        PhoneNumberPublishingHouseDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> phoneNumberPublishingHouseEntity = " + entity
                + " ---> phoneNumberPublishingHouseEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new PhoneNumberPublishingHouseDTO();
            result.setId(entity.getId());
            result.setPhoneNumber(entity.getPhoneNumber());
            result.setCountryCode(entity.getCountryCode());
            result.setType(entity.getType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setPublishingHouseDTO(PublishingHouseConverter.mapEntityToDTO(entity.getPublishingHouseEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<PhoneNumberPublishingHouseDTO> mapListEntityToListDTO(List<PhoneNumberPublishingHouseEntity> entities) {
        List<PhoneNumberPublishingHouseDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberPublishingHouseEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(PhoneNumberPublishingHouseConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static PhoneNumberPublishingHouseEntity mapDTOToEntity(PhoneNumberPublishingHouseDTO dto) {
        PhoneNumberPublishingHouseEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> phoneNumberPublishingHouseDTO = " + dto
                + " ---> phoneNumberPublishingHouseDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new PhoneNumberPublishingHouseEntity();
            result.setId(dto.getId());
            result.setPhoneNumber(dto.getPhoneNumber());
            result.setCountryCode(dto.getCountryCode());
            result.setType(dto.getType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setPublishingHouseEntity(PublishingHouseConverter.mapDTOToEntity(dto.getPublishingHouseDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<PhoneNumberPublishingHouseEntity> mapListDTOToListEntity(List<PhoneNumberPublishingHouseDTO> dtos) {
        List<PhoneNumberPublishingHouseEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberPublishingHouseDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(PhoneNumberPublishingHouseConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
