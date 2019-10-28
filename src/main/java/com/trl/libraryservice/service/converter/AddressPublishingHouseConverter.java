package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressPublishingHouseDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.AddressPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert AddressPublishingHouseEntity to AddressPublishingHouseDTO and vice versa.
 * And also, this class is designed to convert List of AddressPublishingHouseEntity to List AddressPublishingHouseDTO and vice versa.
 */
public final class AddressPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressPublishingHouseConverter.class);

    private AddressPublishingHouseConverter() {
    }

    /**
     * This method is designed to convert AddressPublishingHouseEntity to AddressPublishingHouseDTO.
     *
     * @param entity That be converted to AddressPublishingHouseDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type AddressPublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static AddressPublishingHouseDTO mapEntityToDTO(AddressPublishingHouseEntity entity) throws InvalidArgumentException {
        AddressPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> addressPublishingHouseEntity = " + entity
                + " ---> addressPublishingHouseEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AddressPublishingHouseDTO();
        result.setId(entity.getId());
        result.setCountry(entity.getCountry());
        result.setCity(entity.getCity());
        result.setStreet(entity.getStreet());
        result.setHouseNumber(entity.getHouseNumber());
        result.setPostcode(entity.getPostcode());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of AddressPublishingHouseEntity to List of AddressPublishingHouseDTO.
     *
     * @param entities That be converted to List of AddressPublishingHouseDTO. Parameter 'entities' must not be equal to null.
     * @return An List of AddressPublishingHouseDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<AddressPublishingHouseDTO> mapListEntityToListDTO(List<AddressPublishingHouseEntity> entities) throws InvalidArgumentException {
        List<AddressPublishingHouseDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> addressPublishingHouseEntityList = " + entities);

        for (AddressPublishingHouseEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert AddressPublishingHouseDTO to AddressPublishingHouseEntity.
     *
     * @param dto That be converted to AddressPublishingHouseEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type AddressPublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static AddressPublishingHouseEntity mapDTOToEntity(AddressPublishingHouseDTO dto) throws InvalidArgumentException {
        AddressPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> addressPublishingHouseDTO = " + dto
                + " ---> addressPublishingHouseDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AddressPublishingHouseEntity();
        result.setId(dto.getId());
        result.setCountry(dto.getCountry());
        result.setCity(dto.getCity());
        result.setStreet(dto.getStreet());
        result.setHouseNumber(dto.getHouseNumber());
        result.setPostcode(dto.getPostcode());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of AddressPublishingHouseDTO to List of AddressPublishingHouseEntity.
     *
     * @param dtos That be converted to List of AddressPublishingHouseEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of AddressPublishingHouseEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<AddressPublishingHouseEntity> mapListDTOToListEntity(List<AddressPublishingHouseDTO> dtos) throws InvalidArgumentException {
        List<AddressPublishingHouseEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> addressPublishingHouseDTOList = " + dtos);

        for (AddressPublishingHouseDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}