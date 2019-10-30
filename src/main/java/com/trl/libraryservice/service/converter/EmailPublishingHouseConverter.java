package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailPublishingHouseDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.repository.entity.EmailPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert EmailPublishingHouseEntity to EmailPublishingHouseDTO and vice versa.
 * And also, this class is designed to convert List of EmailPublishingHouseEntity to List EmailPublishingHouseDTO and vice versa.
 */
public final class EmailPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailPublishingHouseConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private EmailPublishingHouseConverter() {
    }

    /**
     * This method is designed to convert EmailPublishingHouseEntity to EmailPublishingHouseDTO.
     *
     * @param entity That be converted to EmailPublishingHouseDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type EmailPublishingHouseDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static EmailPublishingHouseDTO mapEntityToDTO(EmailPublishingHouseEntity entity) throws IllegalMethodParameterException {
        EmailPublishingHouseDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert List of EmailPublishingHouseEntity to List of EmailPublishingHouseDTO.
     *
     * @param entities That be converted to List of EmailPublishingHouseDTO. Parameter 'entities' must not be equal to null.
     * @return An List of EmailPublishingHouseDTO.
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static List<EmailPublishingHouseDTO> mapListEntityToListDTO(List<EmailPublishingHouseEntity> entities) throws IllegalMethodParameterException {
        List<EmailPublishingHouseDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> emailPublishingHouseEntityList = " + entities);

        for (EmailPublishingHouseEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert EmailPublishingHouseDTO to EmailPublishingHouseEntity.
     *
     * @param dto That be converted to EmailPublishingHouseEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type EmailPublishingHouseEntity.
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static EmailPublishingHouseEntity mapDTOToEntity(EmailPublishingHouseDTO dto) throws IllegalMethodParameterException {
        EmailPublishingHouseEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert List of EmailPublishingHouseDTO to List of EmailPublishingHouseEntity.
     *
     * @param dtos That be converted to List of EmailPublishingHouseEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of EmailPublishingHouseEntity.
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static List<EmailPublishingHouseEntity> mapListDTOToListEntity(List<EmailPublishingHouseDTO> dtos) throws IllegalMethodParameterException {
        List<EmailPublishingHouseEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> emailPublishingHouseDTOList = " + dtos);

        for (EmailPublishingHouseDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}