package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PublishingHouseDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
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
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private PublishingHouseConverter() {
    }

    /**
     * This method is designed to convert PublishingHouseEntity to PublishingHouseDTO.
     *
     * @param entity That be converted to PublishingHouseDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type PublishingHouseDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static PublishingHouseDTO mapEntityToDTO(PublishingHouseEntity entity) throws IllegalMethodParameterException {
        PublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> publishingHouseEntity = " + entity
                + " ---> publishingHouseEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new PublishingHouseDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setAddress(AddressPublishingHouseConverter.mapEntityToDTO(entity.getAddress()));
        result.addPhoneNumbers(PhoneNumberPublishingHouseConverter.mapListEntityToListDTO(entity.getPhoneNumbers()));
        result.addEmails(EmailPublishingHouseConverter.mapListEntityToListDTO(entity.getEmails()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PublishingHouseEntity to List of PublishingHouseDTO.
     *
     * @param entities That be converted to List of PublishingHouseDTO. Parameter 'entities' must not be equal to null.
     * @return An List of PublishingHouseDTO.
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static List<PublishingHouseDTO> mapListEntityToListDTO(List<PublishingHouseEntity> entities) throws IllegalMethodParameterException {
        List<PublishingHouseDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static PublishingHouseEntity mapDTOToEntity(PublishingHouseDTO dto) throws IllegalMethodParameterException {
        PublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> publishingHouseDTO = " + dto
                + " ---> publishingHouseDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new PublishingHouseEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setAddress(AddressPublishingHouseConverter.mapDTOToEntity(dto.getAddress()));
        result.addPhoneNumbers(PhoneNumberPublishingHouseConverter.mapListDTOToListEntity(dto.getPhoneNumbers()));
        result.addEmails(EmailPublishingHouseConverter.mapListDTOToListEntity(dto.getEmails()));

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PublishingHouseDTO to List of PublishingHouseEntity.
     *
     * @param dtos That be converted to List of PublishingHouseEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of PublishingHouseEntity.
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static List<PublishingHouseEntity> mapListDTOToListEntity(List<PublishingHouseDTO> dtos) throws IllegalMethodParameterException {
        List<PublishingHouseEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> publishingHouseDTOList = " + dtos);

        for (PublishingHouseDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}