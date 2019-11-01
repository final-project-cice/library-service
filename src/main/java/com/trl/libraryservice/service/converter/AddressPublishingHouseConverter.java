package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.AddressPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal PhoneNumberPublishingHouseEntity} to {@literal PhoneNumberPublishingHouseDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<PhoneNumberPublishingHouseEntity>} to {@literal List<PhoneNumberPublishingHouseDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class AddressPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressPublishingHouseConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private AddressPublishingHouseConverter() {
    }

    /**
     * Convert {@literal AddressPublishingHouseEntity} to {@literal AddressPublishingHouseDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal AddressPublishingHouseDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static AddressPublishingHouseDTO mapEntityToDTO(AddressPublishingHouseEntity entity) {
        AddressPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<AddressPublishingHouseEntity>} to {@literal List<AddressPublishingHouseDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<AddressPublishingHouseDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<AddressPublishingHouseDTO> mapListEntityToListDTO(List<AddressPublishingHouseEntity> entities) {
        List<AddressPublishingHouseDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> addressPublishingHouseEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(AddressPublishingHouseConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal AddressPublishingHouseDTO} to {@literal AddressPublishingHouseEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal AddressPublishingHouseEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static AddressPublishingHouseEntity mapDTOToEntity(AddressPublishingHouseDTO dto) {
        AddressPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<AddressPublishingHouseDTO} to {@literal List<AddressPublishingHouseEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<AddressPublishingHouseEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<AddressPublishingHouseEntity> mapListDTOToListEntity(List<AddressPublishingHouseDTO> dtos) {
        List<AddressPublishingHouseEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> addressPublishingHouseDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(AddressPublishingHouseConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}