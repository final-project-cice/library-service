package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.AddressAuthorDTO;
import com.trl.libraryservice.repository.entity.AddressAuthorEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class AddressAuthorConverter {

    private static final Logger LOG = LoggerFactory.getLogger(AddressAuthorConverter.class);

    private AddressAuthorConverter() { }

    /**
     * @param entity
     * @return
     */
    public static AddressAuthorDTO mapEntityToDTO(AddressAuthorEntity entity) {
        AddressAuthorDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> addressAuthorEntity = " + entity
                + " ---> addressAuthorEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new AddressAuthorDTO();
            result.setId(entity.getId());
            result.setCountry(entity.getCountry());
            result.setCity(entity.getCity());
            result.setStreet(entity.getStreet());
            result.setHouseNumber(entity.getHouseNumber());
            result.setPostcode(entity.getPostcode());
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
    public static List<AddressAuthorDTO> mapListEntityToListDTO(List<AddressAuthorEntity> entities) {
        List<AddressAuthorDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> addressAuthorEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(AddressAuthorConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static AddressAuthorEntity mapDTOToEntity(AddressAuthorDTO dto) {
        AddressAuthorEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> addressAuthorDTO = " + dto
                + " ---> addressAuthorDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new AddressAuthorEntity();
            result.setId(dto.getId());
            result.setCountry(dto.getCountry());
            result.setCity(dto.getCity());
            result.setStreet(dto.getStreet());
            result.setHouseNumber(dto.getHouseNumber());
            result.setPostcode(dto.getPostcode());
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
    public static List<AddressAuthorEntity> mapListDTOToListEntity(List<AddressAuthorDTO> dtos) {
        List<AddressAuthorEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> addressAuthorDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(AddressAuthorConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
