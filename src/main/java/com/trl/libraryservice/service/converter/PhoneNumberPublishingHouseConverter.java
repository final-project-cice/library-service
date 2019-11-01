package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.PhoneNumberPublishingHouseEntity;

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
public final class PhoneNumberPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberPublishingHouseConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private PhoneNumberPublishingHouseConverter() {
    }

    /**
     * Convert {@literal PhoneNumberPublishingHouseEntity} to {@literal PhoneNumberPublishingHouseDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal PhoneNumberPublishingHouseDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static PhoneNumberPublishingHouseDTO mapEntityToDTO(PhoneNumberPublishingHouseEntity entity) {
        PhoneNumberPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<PhoneNumberPublishingHouseEntity>} to {@literal List<PhoneNumberPublishingHouseDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<PhoneNumberPublishingHouseDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<PhoneNumberPublishingHouseDTO> mapListEntityToListDTO(List<PhoneNumberPublishingHouseEntity> entities) {
        List<PhoneNumberPublishingHouseDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberPublishingHouseEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(PhoneNumberPublishingHouseConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal PhoneNumberPublishingHouseDTO} to {@literal PhoneNumberPublishingHouseEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal PhoneNumberPublishingHouseEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static PhoneNumberPublishingHouseEntity mapDTOToEntity(PhoneNumberPublishingHouseDTO dto) {
        PhoneNumberPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<PhoneNumberPublishingHouseDTO} to {@literal List<PhoneNumberPublishingHouseEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<PhoneNumberPublishingHouseEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<PhoneNumberPublishingHouseEntity> mapListDTOToListEntity(List<PhoneNumberPublishingHouseDTO> dtos) {
        List<PhoneNumberPublishingHouseEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberPublishingHouseDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(PhoneNumberPublishingHouseConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
