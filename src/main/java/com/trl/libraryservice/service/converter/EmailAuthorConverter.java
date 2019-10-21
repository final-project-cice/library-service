package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.EmailAuthorDTO;
import com.trl.libraryservice.repository.entity.EmailAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class EmailAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAuthorConverter.class);

    private EmailAuthorConverter() { }

    /**
     * @param entity
     * @return
     */
    public static EmailAuthorDTO mapEntityToDTO(EmailAuthorEntity entity) {
        EmailAuthorDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> emailAuthorEntity = " + entity
                + " ---> emailAuthorEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new EmailAuthorDTO();
            result.setId(entity.getId());
            result.setEmail(entity.getEmail());
            result.setEmailType(entity.getEmailType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setAuthorDTO(AuthorConverter.mapEntityToDTO(entity.getAuthorEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<EmailAuthorDTO> mapListEntityToListDTO(List<EmailAuthorEntity> entities) {
        List<EmailAuthorDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> emailAuthorEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(EmailAuthorConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static EmailAuthorEntity mapDTOToEntity(EmailAuthorDTO dto) {
        EmailAuthorEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> emailAuthorDTO = " + dto
                + " ---> emailAuthorDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new EmailAuthorEntity();
            result.setId(dto.getId());
            result.setEmail(dto.getEmail());
            result.setEmailType(dto.getEmailType());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setAuthorEntity(AuthorConverter.mapDTOToEntity(dto.getAuthorDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<EmailAuthorEntity> mapListDTOToListEntity(List<EmailAuthorDTO> dtos) {
        List<EmailAuthorEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> emailAuthorDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(EmailAuthorConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
