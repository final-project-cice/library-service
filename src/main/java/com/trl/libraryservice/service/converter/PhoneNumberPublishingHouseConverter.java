package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberPublishingHouseDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.PhoneNumberPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert PhoneNumberPublishingHouseEntity to PhoneNumberPublishingHouseDTO and vice versa.
 * And also, this class is designed to convert List of PhoneNumberPublishingHouseEntity to List PhoneNumberPublishingHouseDTO and vice versa.
 */
public final class PhoneNumberPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberPublishingHouseConverter.class);

    private PhoneNumberPublishingHouseConverter() {
    }

    /**
     * This method is designed to convert PhoneNumberPublishingHouseEntity to PhoneNumberPublishingHouseDTO.
     *
     * @param entity That be converted to PhoneNumberPublishingHouseDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type PhoneNumberPublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static PhoneNumberPublishingHouseDTO mapEntityToDTO(PhoneNumberPublishingHouseEntity entity) throws InvalidArgumentException {
        PhoneNumberPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> phoneNumberPublishingHouseEntity = " + entity
                + " ---> phoneNumberPublishingHouseEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new PhoneNumberPublishingHouseDTO();
        result.setId(entity.getId());
        result.setPhoneNumber(entity.getPhoneNumber());
        result.setCountryCode(entity.getCountryCode());
        result.setType(entity.getType());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PhoneNumberPublishingHouseEntity to List of PhoneNumberPublishingHouseDTO.
     *
     * @param entities That be converted to List of PhoneNumberPublishingHouseDTO. Parameter 'entities' must not be equal to null.
     * @return An List of PhoneNumberPublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<PhoneNumberPublishingHouseDTO> mapListEntityToListDTO(List<PhoneNumberPublishingHouseEntity> entities) throws InvalidArgumentException {
        List<PhoneNumberPublishingHouseDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberPublishingHouseEntityList = " + entities);

        for (PhoneNumberPublishingHouseEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert PhoneNumberPublishingHouseDTO to PhoneNumberPublishingHouseEntity.
     *
     * @param dto That be converted to PhoneNumberPublishingHouseEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type PhoneNumberPublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static PhoneNumberPublishingHouseEntity mapDTOToEntity(PhoneNumberPublishingHouseDTO dto) throws InvalidArgumentException {
        PhoneNumberPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> phoneNumberPublishingHouseDTO = " + dto
                + " ---> phoneNumberPublishingHouseDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new PhoneNumberPublishingHouseEntity();
        result.setId(dto.getId());
        result.setPhoneNumber(dto.getPhoneNumber());
        result.setCountryCode(dto.getCountryCode());
        result.setType(dto.getType());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PhoneNumberPublishingHouseDTO to List of PhoneNumberPublishingHouseEntity.
     *
     * @param dtos That be converted to List of PhoneNumberPublishingHouseEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of PhoneNumberPublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<PhoneNumberPublishingHouseEntity> mapListDTOToListEntity(List<PhoneNumberPublishingHouseDTO> dtos) throws InvalidArgumentException {
        List<PhoneNumberPublishingHouseEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberPublishingHouseDTOList = " + dtos);

        for (PhoneNumberPublishingHouseDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
