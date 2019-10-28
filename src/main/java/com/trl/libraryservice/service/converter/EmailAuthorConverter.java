package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailAuthorDTO;
import com.trl.libraryservice.exception.InvalidArgumentException;
import com.trl.libraryservice.repository.entity.EmailAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert EmailAuthorEntity to EmailAuthorDTO and vice versa.
 * And also, this class is designed to convert List of EmailAuthorEntity to List EmailAuthorDTO and vice versa.
 */
public final class EmailAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAuthorConverter.class);

    private EmailAuthorConverter() {
    }

    /**
     * This method is designed to convert EmailAuthorEntity to EmailAuthorDTO.
     *
     * @param entity That be converted to EmailAuthorDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type EmailAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entity' is equal null value.
     */
    public static EmailAuthorDTO mapEntityToDTO(EmailAuthorEntity entity) throws InvalidArgumentException {
        EmailAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
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
     * This method is designed to convert List of EmailAuthorEntity to List of EmailAuthorDTO.
     *
     * @param entities That be converted to List of EmailAuthorDTO. Parameter 'entities' must not be equal to null.
     * @return An List of EmailAuthorDTO.
     * @throws InvalidArgumentException If parameter 'entities' is equal null value.
     */
    public static List<EmailAuthorDTO> mapListEntityToListDTO(List<EmailAuthorEntity> entities) throws InvalidArgumentException {
        List<EmailAuthorDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListEntityToListDTO() ---> emailAuthorEntityList = " + entities);

        for (EmailAuthorEntity entity : entities) {
            resultList.add(mapEntityToDTO(entity));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert EmailAuthorDTO to EmailAuthorEntity.
     *
     * @param dto That be converted to EmailAuthorEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type EmailAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dto' is equal null value.
     */
    public static EmailAuthorEntity mapDTOToEntity(EmailAuthorDTO dto) throws InvalidArgumentException {
        EmailAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
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
     * This method is designed to convert List of EmailAuthorDTO to List of EmailAuthorEntity.
     *
     * @param dtos That be converted to List of EmailAuthorEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of EmailAuthorEntity.
     * @throws InvalidArgumentException If parameter 'dtos' is equal null value.
     */
    public static List<EmailAuthorEntity> mapListDTOToListEntity(List<EmailAuthorDTO> dtos) throws InvalidArgumentException {
        List<EmailAuthorEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> "
                    + "One of the parameters is incorrect, check the parameters that are passed to the method.");
            throw new InvalidArgumentException(
                    "One of the parameters is incorrect, check the parameters that are passed to the method.");
        }

        LOG.debug("************ mapListDTOToListEntity() ---> emailAuthorDTOList = " + dtos);

        for (EmailAuthorDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
