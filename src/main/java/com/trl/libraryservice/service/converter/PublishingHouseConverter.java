package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PublishingHouseDTO;
import com.trl.libraryservice.repository.entity.PublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class PublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PublishingHouseConverter.class);

    private PublishingHouseConverter() { }

    /**
     *
     * */
    public static PublishingHouseDTO mapEntityToDTO(PublishingHouseEntity entity) {
        PublishingHouseDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> publishingHouseEntity = " + entity
                + " ---> publishingHouseEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new PublishingHouseDTO();
            result.setId(entity.getId());
            result.setName(entity.getName());
            result.setAddressPublishingHouseDTOS(
                    AddressPublishingHouseConverter.mapListEntityToListDTO(entity.getAddressPublishingHouseEntities())
            );
            result.setPhoneNumberPublishingHouseDTOS(
                    PhoneNumberPublishingHouseConverter.mapListEntityToListDTO(entity.getPhoneNumberPublishingHouseEntities())
            );
            result.setEmailPublishingHouseDTOS(
                    EmailPublishingHouseConverter.mapListEntityToListDTO(entity.getEmailPublishingHouseEntities())
            );
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<PublishingHouseDTO> mapListEntityToListDTO(List<PublishingHouseEntity> entities) {
        List<PublishingHouseDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> publishingHouseEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(PublishingHouseConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static PublishingHouseEntity mapDTOToEntity(PublishingHouseDTO dto) {
        PublishingHouseEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> publishingHouseDTO = " + dto
                + " ---> publishingHouseDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new PublishingHouseEntity();
            result.setId(dto.getId());
            result.setName(dto.getName());
            result.setAddressPublishingHouseEntities(
                    AddressPublishingHouseConverter.mapListDTOToListEntity(dto.getAddressPublishingHouseDTOS())
            );
            result.setPhoneNumberPublishingHouseEntities(
                    PhoneNumberPublishingHouseConverter.mapListDTOToListEntity(dto.getPhoneNumberPublishingHouseDTOS())
            );
            result.setEmailPublishingHouseEntities(
                    EmailPublishingHouseConverter.mapListDTOToListEntity(dto.getEmailPublishingHouseDTOS())
            );
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<PublishingHouseEntity> mapListDTOToListEntity(List<PublishingHouseDTO> dtos) {
        List<PublishingHouseEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> publishingHouseDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(PublishingHouseConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
