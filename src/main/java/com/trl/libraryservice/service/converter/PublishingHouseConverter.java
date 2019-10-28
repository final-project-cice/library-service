package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PublishingHouseDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.PublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert PublishingHouseEntity to PublishingHouseDTO and vice versa.
 * And also, this class is designed to convert List of PublishingHouseEntity to List PublishingHouseDTO and vice versa.
 */
public final class PublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PublishingHouseConverter.class);

    private PublishingHouseConverter() {
    }

    /**
     * This method is designed to convert PublishingHouseEntity to PublishingHouseDTO.
     *
     * @param entity That be converted to PublishingHouseDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type PublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static PublishingHouseDTO mapEntityToDTO(PublishingHouseEntity entity) throws InvalidArgumentException {
        PublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> publishingHouseEntity = " + entity
                + " ---> publishingHouseEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new PublishingHouseDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setAddress(
                AddressPublishingHouseConverter.mapEntityToDTO(entity.getAddress())
        );
        result.setPhoneNumbers(
                PhoneNumberPublishingHouseConverter.mapListEntityToListDTO(entity.getPhoneNumbers())
        );
        result.setEmails(
                EmailPublishingHouseConverter.mapListEntityToListDTO(entity.getEmails())
        );

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PublishingHouseEntity to List of PublishingHouseDTO.
     *
     * @param entities That be converted to List of PublishingHouseDTO. Parameter 'entities' must not be equal to null.
     * @return An List of PublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<PublishingHouseDTO> mapListEntityToListDTO(List<PublishingHouseEntity> entities) throws InvalidArgumentException {
        List<PublishingHouseDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> publishingHouseEntityList = " + entities);

        for (PublishingHouseEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert PublishingHouseDTO to PublishingHouseEntity.
     *
     * @param dto That be converted to PublishingHouseEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type PublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static PublishingHouseEntity mapDTOToEntity(PublishingHouseDTO dto) throws InvalidArgumentException {
        PublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> publishingHouseDTO = " + dto
                + " ---> publishingHouseDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new PublishingHouseEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setAddress(
                AddressPublishingHouseConverter.mapDTOToEntity(dto.getAddress())
        );
        result.setPhoneNumbers(
                PhoneNumberPublishingHouseConverter.mapListDTOToListEntity(dto.getPhoneNumbers())
        );
        result.setEmails(
                EmailPublishingHouseConverter.mapListDTOToListEntity(dto.getEmails())
        );

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PublishingHouseDTO to List of PublishingHouseEntity.
     *
     * @param dtos That be converted to List of PublishingHouseEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of PublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<PublishingHouseEntity> mapListDTOToListEntity(List<PublishingHouseDTO> dtos) throws InvalidArgumentException {
        List<PublishingHouseEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> publishingHouseDTOList = " + dtos);

        for (PublishingHouseDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}