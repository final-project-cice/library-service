package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.repository.entity.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);

    private UserConverter() {
    }

    /**
     * @param entity
     * @return
     */
    public static UserDTO mapEntityToDTO(UserEntity entity) {
        UserDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> userEntity = " + entity
                + " ---> userEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new UserDTO();
            result.setId(entity.getId());
            result.setFirstName(entity.getFirstName());
            result.setLastName(entity.getLastName());
            result.setEmail(entity.getEmail());
            result.setBirthday(entity.getBirthday());
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<UserDTO> mapListEntityToListDTO(List<UserEntity> entities) {
        List<UserDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> userEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(UserConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static UserEntity mapDTOToEntity(UserDTO dto) {
        UserEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> userDTO = " + dto
                + " ---> userDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new UserEntity();
            result.setId(dto.getId());
            result.setFirstName(dto.getFirstName());
            result.setLastName(dto.getLastName());
            result.setEmail(dto.getEmail());
            result.setBirthday(dto.getBirthday());
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<UserEntity> mapListDTOToListEntity(List<UserDTO> dtos) {
        List<UserEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> userDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(UserConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
