package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.IllegalMethodParameterException;
import com.trl.libraryservice.repository.entity.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to convert UserEntity to UserDTO and vice versa.
 * And also, this class is designed to convert List of UserEntity to List UserDTO and vice versa.
 */
public final class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private UserConverter() {
    }

    /**
     * This method is designed to convert UserEntity to UserDTO.
     *
     * @param entity That be converted to UserDTO. Parameter 'entity' must not be equal to null.
     * @return An object of type UserDTO.
     * @throws IllegalMethodParameterException If parameter 'entity' is equal null value.
     */
    public static UserDTO mapEntityToDTO(UserEntity entity) throws IllegalMethodParameterException {
        UserDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert List of UserEntity to List of UserDTO.
     *
     * @param entities That be converted to List of UserDTO. Parameter 'entities' must not be equal to null.
     * @return An List of UserDTO.
     * @throws IllegalMethodParameterException If parameter 'entities' is equal null value.
     */
    public static List<UserDTO> mapListEntityToListDTO(List<UserEntity> entities) throws IllegalMethodParameterException {
        List<UserDTO> resultList = new ArrayList<>();

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> userEntityList = " + entities);

        for (UserEntity e : entities) {
            resultList.add(mapEntityToDTO(e));
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * This method is designed to convert UserDTO to UserEntity.
     *
     * @param dto That be converted to UserEntity. Parameter 'dto' must not be equal to null.
     * @return An object of type UserEntity.
     * @throws IllegalMethodParameterException If parameter 'dto' is equal null value.
     */
    public static UserEntity mapDTOToEntity(UserDTO dto) throws IllegalMethodParameterException {
        UserEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
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
     * This method is designed to convert List of UserDTO to List of UserEntity.
     *
     * @param dtos That be converted to List of UserEntity. Parameter 'dtos' must not be equal to null.
     * @return An List of UserEntity.
     * @throws IllegalMethodParameterException If parameter 'dtos' is equal null value.
     */
    public static List<UserEntity> mapListDTOToListEntity(List<UserDTO> dtos) throws IllegalMethodParameterException {
        List<UserEntity> resultList = new ArrayList<>();

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalMethodParameterException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> userDTOList = " + dtos);

        for (UserDTO dto : dtos) {
            resultList.add(mapDTOToEntity(dto));
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
