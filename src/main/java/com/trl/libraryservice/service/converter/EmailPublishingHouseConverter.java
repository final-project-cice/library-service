package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.EmailPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal EmailPublishingHouseEntity} to {@literal EmailPublishingHouseDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<EmailPublishingHouseEntity>} to {@literal List<EmailPublishingHouseDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class EmailPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailPublishingHouseConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private EmailPublishingHouseConverter() {
    }

    /**
     * Convert {@literal EmailPublishingHouseEntity} to {@literal EmailPublishingHouseDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal EmailPublishingHouseDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static EmailPublishingHouseDTO mapEntityToDTO(EmailPublishingHouseEntity entity) {
        EmailPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> emailPublishingHouseEntity = " + entity
                + " ---> emailPublishingHouseEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new EmailPublishingHouseDTO();
        result.setId(entity.getId());
        result.setEmail(entity.getEmail());
        result.setEmailType(entity.getEmailType());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<EmailPublishingHouseEntity>} to {@literal List<EmailPublishingHouseDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<EmailPublishingHouseDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<EmailPublishingHouseDTO> mapListEntityToListDTO(List<EmailPublishingHouseEntity> entities) {
        List<EmailPublishingHouseDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> emailPublishingHouseEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(EmailPublishingHouseConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal EmailPublishingHouseDTO} to {@literal EmailPublishingHouseEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal EmailPublishingHouseEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static EmailPublishingHouseEntity mapDTOToEntity(EmailPublishingHouseDTO dto) {
        EmailPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> emailPublishingHouseDTO = " + dto
                + " ---> emailPublishingHouseDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new EmailPublishingHouseEntity();
        result.setId(dto.getId());
        result.setEmail(dto.getEmail());
        result.setEmailType(dto.getEmailType());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<EmailPublishingHouseDTO} to {@literal List<EmailPublishingHouseEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<EmailPublishingHouseEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<EmailPublishingHouseEntity> mapListDTOToListEntity(List<EmailPublishingHouseDTO> dtos) {
        List<EmailPublishingHouseEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> emailPublishingHouseDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(EmailPublishingHouseConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}