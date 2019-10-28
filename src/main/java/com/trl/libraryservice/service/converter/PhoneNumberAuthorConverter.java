package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberAuthorDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.PhoneNumberAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert PhoneNumberAuthorEntity to PhoneNumberAuthorDTO and vice versa.
 * And also, this class is designed to convert List of PhoneNumberAuthorEntity to List PhoneNumberAuthorDTO and vice versa.
 */
public final class PhoneNumberAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberAuthorConverter.class);

    private PhoneNumberAuthorConverter() {
    }

    /**
     * This method is designed to convert PhoneNumberAuthorEntity to PhoneNumberAuthorDTO.
     *
     * @param entity That be converted to PhoneNumberAuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type PhoneNumberAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static PhoneNumberAuthorDTO mapEntityToDTO(PhoneNumberAuthorEntity entity) throws InvalidArgumentException {
        PhoneNumberAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> phoneNumberAuthorEntity = " + entity
                + " ---> phoneNumberAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new PhoneNumberAuthorDTO();
        result.setId(entity.getId());
        result.setPhoneNumber(entity.getPhoneNumber());
        result.setCountryCode(entity.getCountryCode());
        result.setType(entity.getType());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PhoneNumberAuthorEntity to List of PhoneNumberAuthorDTO.
     *
     * @param entities That be converted to List of PhoneNumberAuthorDTO. Parameter 'entities' must not be equal to null.
     * @return An List of PhoneNumberAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<PhoneNumberAuthorDTO> mapListEntityToListDTO(List<PhoneNumberAuthorEntity> entities) throws InvalidArgumentException {
        List<PhoneNumberAuthorDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberAuthorEntityList = " + entities);

        for (PhoneNumberAuthorEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert PhoneNumberAuthorDTO to PhoneNumberAuthorEntity.
     *
     * @param dto That be converted to PhoneNumberAuthorEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type PhoneNumberAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static PhoneNumberAuthorEntity mapDTOToEntity(PhoneNumberAuthorDTO dto) throws InvalidArgumentException {
        PhoneNumberAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> phoneNumberAuthorDTO = " + dto
                + " ---> phoneNumberAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new PhoneNumberAuthorEntity();
        result.setId(dto.getId());
        result.setPhoneNumber(dto.getPhoneNumber());
        result.setCountryCode(dto.getCountryCode());
        result.setType(dto.getType());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of PhoneNumberAuthorDTO to List of PhoneNumberAuthorEntity.
     *
     * @param dtos That be converted to List of PhoneNumberAuthorEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of PhoneNumberAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<PhoneNumberAuthorEntity> mapListDTOToListEntity(List<PhoneNumberAuthorDTO> dtos) throws InvalidArgumentException {
        List<PhoneNumberAuthorEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberAuthorDTOList = " + dtos);

        for (PhoneNumberAuthorDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}