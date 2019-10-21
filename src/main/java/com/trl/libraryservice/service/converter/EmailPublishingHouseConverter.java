package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailPublishingHouseDTO;
import com.trl.libraryservice.repository.entity.EmailPublishingHouseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class EmailPublishingHouseConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailPublishingHouseConverter.class);

    private EmailPublishingHouseConverter() { }

    public static EmailPublishingHouseDTO mapEntityToDTO(EmailPublishingHouseEntity entity) {
        EmailPublishingHouseDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> emailPublishingHouseEntity = " + entity
                + " ---> emailPublishingHouseEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new EmailPublishingHouseDTO();
            result.setId(entity.getId());
            result.setEmail(entity.getEmail());
            result.setEmailType(entity.getEmailType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setPublishingHouseDTO(PublishingHouseConverter.mapEntityToDTO(entity.getPublishingHouseEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<EmailPublishingHouseDTO> mapListEntityToListDTO(List<EmailPublishingHouseEntity> entities) {
        List<EmailPublishingHouseDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> emailPublishingHouseEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(EmailPublishingHouseConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static EmailPublishingHouseEntity mapDTOToEntity(EmailPublishingHouseDTO dto) {
        EmailPublishingHouseEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> emailPublishingHouseDTO = " + dto
                + " ---> emailPublishingHouseDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new EmailPublishingHouseEntity();
            result.setId(dto.getId());
            result.setEmail(dto.getEmail());
            result.setEmailType(dto.getEmailType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setPublishingHouseEntity(PublishingHouseConverter.mapDTOToEntity(dto.getPublishingHouseDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<EmailPublishingHouseEntity> mapListDTOToListEntity(List<EmailPublishingHouseDTO> dtos) {
        List<EmailPublishingHouseEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> emailPublishingHouseDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(EmailPublishingHouseConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
