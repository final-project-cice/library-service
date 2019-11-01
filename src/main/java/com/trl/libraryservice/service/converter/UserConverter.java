package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.repository.entity.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal UserEntity} to {@literal UserDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<UserEntity>} to {@literal List<UserDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private UserConverter() {
    }

    /**
     * Convert {@literal UserEntity} to {@literal UserDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal UserDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static UserDTO mapEntityToDTO(UserEntity entity) {
        UserDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> userEntity = " + entity
                + " ---> userEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new UserDTO();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setEmail(entity.getEmail());
        result.setBirthday(entity.getBirthday());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<UserEntity>} to {@literal List<UserDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<UserDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<UserDTO> mapListEntityToListDTO(List<UserEntity> entities) {
        List<UserDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> userEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(UserConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal UserDTO} to {@literal UserEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal UserEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static UserEntity mapDTOToEntity(UserDTO dto) {
        UserEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> userDTO = " + dto
                + " ---> userDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new UserEntity();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setEmail(dto.getEmail());
        result.setBirthday(dto.getBirthday());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<UserDTO} to {@literal List<UserEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<UserEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<UserEntity> mapListDTOToListEntity(List<UserDTO> dtos) {
        List<UserEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> userDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(UserConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
