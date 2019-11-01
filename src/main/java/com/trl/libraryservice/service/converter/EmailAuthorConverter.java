package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailAuthorDTO;
import com.trl.libraryservice.repository.entity.EmailAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal EmailAuthorEntity} to {@literal EmailAuthorDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<EmailAuthorEntity>} to {@literal List<EmailAuthorDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class EmailAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAuthorConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private EmailAuthorConverter() {
    }

    /**
     * Convert {@literal EmailAuthorEntity} to {@literal EmailAuthorDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal EmailAuthorDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static EmailAuthorDTO mapEntityToDTO(EmailAuthorEntity entity) {
        EmailAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> emailAuthorEntity = " + entity
                + " ---> emailAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new EmailAuthorDTO();
        result.setId(entity.getId());
        result.setEmail(entity.getEmail());
        result.setEmailType(entity.getEmailType());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<EmailAuthorEntity>} to {@literal List<EmailAuthorDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<EmailAuthorDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<EmailAuthorDTO> mapListEntityToListDTO(List<EmailAuthorEntity> entities) {
        List<EmailAuthorDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> emailAuthorEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(EmailAuthorConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal EmailAuthorDTO} to {@literal EmailAuthorEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal EmailAuthorEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static EmailAuthorEntity mapDTOToEntity(EmailAuthorDTO dto) {
        EmailAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> emailAuthorDTO = " + dto
                + " ---> emailAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new EmailAuthorEntity();
        result.setId(dto.getId());
        result.setEmail(dto.getEmail());
        result.setEmailType(dto.getEmailType());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<EmailAuthorDTO} to {@literal List<EmailAuthorEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<EmailAuthorEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<EmailAuthorEntity> mapListDTOToListEntity(List<EmailAuthorDTO> dtos) {
        List<EmailAuthorEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> emailAuthorDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(EmailAuthorConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
