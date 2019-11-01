package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PublishingHouseDTO;
import com.trl.libraryservice.repository.entity.PublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal PublishingHouseEntity} to {@literal PublishingHouseDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<PublishingHouseEntity>} to {@literal List<PublishingHouseDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class PublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PublishingHouseConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private PublishingHouseConverter() {
    }

    /**
     * Convert {@literal PublishingHouseEntity} to {@literal PublishingHouseDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal PublishingHouseDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static PublishingHouseDTO mapEntityToDTO(PublishingHouseEntity entity) {
        PublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<PublishingHouseEntity>} to {@literal List<PublishingHouseDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<PublishingHouseDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<PublishingHouseDTO> mapListEntityToListDTO(List<PublishingHouseEntity> entities) {
        List<PublishingHouseDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> publishingHouseEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(PublishingHouseConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal PublishingHouseDTO} to {@literal PublishingHouseEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal PublishingHouseEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static PublishingHouseEntity mapDTOToEntity(PublishingHouseDTO dto) {
        PublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
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
     * Convert {@literal List<PublishingHouseDTO} to {@literal List<PublishingHouseEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<PublishingHouseEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<PublishingHouseEntity> mapListDTOToListEntity(List<PublishingHouseDTO> dtos) {
        List<PublishingHouseEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> publishingHouseDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(PublishingHouseConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}