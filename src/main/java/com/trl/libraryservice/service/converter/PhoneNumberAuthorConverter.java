package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.PhoneNumberAuthorDTO;
import com.trl.libraryservice.repository.entity.PhoneNumberAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal PhoneNumberAuthorEntity} to {@literal PhoneNumberAuthorDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<PhoneNumberAuthorEntity>} to {@literal List<PhoneNumberAuthorDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class PhoneNumberAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberAuthorConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private PhoneNumberAuthorConverter() {
    }

    /**
     * Convert {@literal PhoneNumberAuthorEntity} to {@literal PhoneNumberAuthorDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal PhoneNumberAuthorDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static PhoneNumberAuthorDTO mapEntityToDTO(PhoneNumberAuthorEntity entity) {
        PhoneNumberAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> phoneNumberAuthorEntity = " + entity
                + " ---> phoneNumberAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new PhoneNumberAuthorDTO();
        result.setId(entity.getId());
        result.setPhoneNumber(entity.getPhoneNumber());
        result.setCountryCode(entity.getCountryCode());
        result.setType(entity.getType());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<PhoneNumberAuthorEntity>} to {@literal List<PhoneNumberAuthorDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<PhoneNumberAuthorDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<PhoneNumberAuthorDTO> mapListEntityToListDTO(List<PhoneNumberAuthorEntity> entities) {
        List<PhoneNumberAuthorDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> phoneNumberAuthorEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(PhoneNumberAuthorConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal PhoneNumberAuthorDTO} to {@literal PhoneNumberAuthorEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal PhoneNumberAuthorEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static PhoneNumberAuthorEntity mapDTOToEntity(PhoneNumberAuthorDTO dto) {
        PhoneNumberAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> phoneNumberAuthorDTO = " + dto
                + " ---> phoneNumberAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new PhoneNumberAuthorEntity();
        result.setId(dto.getId());
        result.setPhoneNumber(dto.getPhoneNumber());
        result.setCountryCode(dto.getCountryCode());
        result.setType(dto.getType());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<PhoneNumberAuthorDTO} to {@literal List<PhoneNumberAuthorEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<PhoneNumberAuthorEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<PhoneNumberAuthorEntity> mapListDTOToListEntity(List<PhoneNumberAuthorDTO> dtos) {
        List<PhoneNumberAuthorEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> phoneNumberAuthorDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(PhoneNumberAuthorConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}