package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressAuthorDTO;
import com.trl.libraryservice.repository.entity.AddressAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal AddressAuthorEntity} to {@literal AddressAuthorDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<AddressAuthorEntity>} to {@literal List<AddressAuthorDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class AddressAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressAuthorConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private AddressAuthorConverter() {
    }

    /**
     * Convert {@literal AddressAuthorEntity} to {@literal AddressAuthorDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal AddressAuthorDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static AddressAuthorDTO mapEntityToDTO(AddressAuthorEntity entity) {
        AddressAuthorDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> addressAuthorEntity = " + entity
                + " ---> addressAuthorEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new AddressAuthorDTO();
        result.setId(entity.getId());
        result.setCountry(entity.getCountry());
        result.setCity(entity.getCity());
        result.setStreet(entity.getStreet());
        result.setHouseNumber(entity.getHouseNumber());
        result.setPostcode(entity.getPostcode());

        LOG.debug("************ mapEntityToDTO() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<AddressAuthorEntity>} to {@literal List<AddressAuthorDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<AddressAuthorDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<AddressAuthorDTO> mapListEntityToListDTO(List<AddressAuthorEntity> entities) {
        List<AddressAuthorDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> addressAuthorEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(AddressAuthorConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal AddressAuthorDTO} to {@literal AddressAuthorEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal AddressAuthorEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static AddressAuthorEntity mapDTOToEntity(AddressAuthorDTO dto) {
        AddressAuthorEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> addressAuthorDTO = " + dto
                + " ---> addressAuthorDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new AddressAuthorEntity();
        result.setId(dto.getId());
        result.setCountry(dto.getCountry());
        result.setCity(dto.getCity());
        result.setStreet(dto.getStreet());
        result.setHouseNumber(dto.getHouseNumber());
        result.setPostcode(dto.getPostcode());

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<AddressAuthorDTO} to {@literal List<AddressAuthorEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<AddressAuthorEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<AddressAuthorEntity> mapListDTOToListEntity(List<AddressAuthorDTO> dtos) {
        List<AddressAuthorEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> addressAuthorDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(AddressAuthorConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}