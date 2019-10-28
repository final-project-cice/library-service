package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressAuthorDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.AddressAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert AddressAuthorEntity to AddressAuthorDTO and vice versa.
 * And also, this class is designed to convert List of AddressAuthorEntity to List AddressAuthorDTO and vice versa.
 */
public final class AddressAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressAuthorConverter.class);

    private AddressAuthorConverter() {
    }

    /**
     * This method is designed to convert AddressAuthorEntity to AddressAuthorDTO.
     *
     * @param entity That be converted to AddressAuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type AddressAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static AddressAuthorDTO mapEntityToDTO(AddressAuthorEntity entity) throws InvalidArgumentException {
        AddressAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapEntityToDTO() ---> addressAuthorEntity = " + entity
                + " ---> addressAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AddressAuthorDTO();
        result.setId(entity.getId());
        result.setCountry(entity.getCountry());
        result.setCity(entity.getCity());
        result.setStreet(entity.getStreet());
        result.setHouseNumber(entity.getHouseNumber());
        result.setPostcode(entity.getPostcode());

        LOG.debug("************ mapEntityToDTO() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of AddressAuthorEntity to List of AddressAuthorDTO.
     *
     * @param entities That be converted to List of AddressAuthorDTO. Parameter 'entities' must not be equal to null.
     * @return An List of AddressAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<AddressAuthorDTO> mapListEntityToListDTO(List<AddressAuthorEntity> entities) throws InvalidArgumentException {
        List<AddressAuthorDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> addressAuthorEntityList = " + entities);

        for (AddressAuthorEntity e : entities) {
            resultList.add(mapEntityToDTO(e));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert AddressAuthorDTO to AddressAuthorEntity.
     *
     * @param dto That be converted to AddressAuthorEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type AddressAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static AddressAuthorEntity mapDTOToEntity(AddressAuthorDTO dto) throws InvalidArgumentException {
        AddressAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapDTOToEntity() ---> addressAuthorDTO = " + dto
                + " ---> addressAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AddressAuthorEntity();
        result.setId(dto.getId());
        result.setCountry(dto.getCountry());
        result.setCity(dto.getCity());
        result.setStreet(dto.getStreet());
        result.setHouseNumber(dto.getHouseNumber());
        result.setPostcode(dto.getPostcode());

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * This method is designed to convert List of AddressAuthorDTO to List of AddressAuthorEntity.
     *
     * @param dtos That be converted to List of AddressAuthorEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of AddressAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<AddressAuthorEntity> mapListDTOToListEntity(List<AddressAuthorDTO> dtos) throws InvalidArgumentException {
        List<AddressAuthorEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> addressAuthorDTOList = " + dtos);

        for (AddressAuthorDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}