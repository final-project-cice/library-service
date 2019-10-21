package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.AddressPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class AddressPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressPublishingHouseConverter.class);

    private AddressPublishingHouseConverter() { }

    public static AddressPublishingHouseDTO mapEntityToDTO(AddressPublishingHouseEntity entity) {
        AddressPublishingHouseDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> addressPublishingHouseEntity = " + entity
                + " ---> addressPublishingHouseEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new AddressPublishingHouseDTO();
            result.setId(entity.getId());
            result.setCountry(entity.getCountry());
            result.setCity(entity.getCity());
            result.setStreet(entity.getStreet());
            result.setHouseNumber(entity.getHouseNumber());
            result.setPostcode(entity.getPostcode());
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
    public static List<AddressPublishingHouseDTO> mapListEntityToListDTO(List<AddressPublishingHouseEntity> entities) {
        List<AddressPublishingHouseDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> addressPublishingHouseEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(AddressPublishingHouseConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static AddressPublishingHouseEntity mapDTOToEntity(AddressPublishingHouseDTO dto) {
        AddressPublishingHouseEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> addressPublishingHouseDTO = " + dto
                + " ---> addressPublishingHouseDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new AddressPublishingHouseEntity();
            result.setId(dto.getId());
            result.setCountry(dto.getCountry());
            result.setCity(dto.getCity());
            result.setStreet(dto.getStreet());
            result.setHouseNumber(dto.getHouseNumber());
            result.setPostcode(dto.getPostcode());
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
    public static List<AddressPublishingHouseEntity> mapListDTOToListEntity(List<AddressPublishingHouseDTO> dtos) {
        List<AddressPublishingHouseEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> addressPublishingHouseDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(AddressPublishingHouseConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
