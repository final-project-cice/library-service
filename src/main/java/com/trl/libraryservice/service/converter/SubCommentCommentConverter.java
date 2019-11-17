package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal SubCommentCommentEntity} to {@literal SubCommentCommentDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<SubCommentCommentEntity>} to {@literal List<SubCommentCommentDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class SubCommentCommentConverter {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private SubCommentCommentConverter() {
    }

    /**
     * Convert {@literal SubCommentCommentEntity} to {@literal SubCommentCommentDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal SubCommentCommentDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static SubCommentCommentDTO mapEntityToDTO(SubCommentCommentEntity entity) {
        SubCommentCommentDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> subCommentCommentEntity = " + entity
                + " ---> subCommentCommentEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new SubCommentCommentDTO();
        result.setId(entity.getId());
        result.setUserId(entity.getUserId());
        result.setText(entity.getText());
        result.setDate(entity.getDate());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<SubCommentCommentEntity>} to {@literal List<SubCommentCommentDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<SubCommentCommentDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<SubCommentCommentDTO> mapListEntityToListDTO(List<SubCommentCommentEntity> entities) {
        List<SubCommentCommentDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> subCommentCommentEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(SubCommentCommentConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal Page<SubCommentCommentEntity>} to {@literal Page<SubCommentCommentDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Page<SubCommentCommentDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Page<SubCommentCommentDTO> mapPageEntityToPageDTO(Page<SubCommentCommentEntity> entities) {
        Page<SubCommentCommentDTO> resultPage;

        if (entities == null) {
            LOG.debug("************ mapPageEntityToPageDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapPageEntityToPageDTO() ---> subCommentCommentEntityList = " + entities);

        resultPage = entities.map(SubCommentCommentConverter::mapEntityToDTO);

        LOG.debug("************ mapPageEntityToPageDTO() ---> resultPage = " + resultPage);

        return resultPage;
    }

    /**
     * Convert {@literal SubCommentCommentDTO} to {@literal SubCommentCommentEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal SubCommentCommentEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static SubCommentCommentEntity mapDTOToEntity(SubCommentCommentDTO dto) {
        SubCommentCommentEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> subCommentCommentDTO = " + dto
                + " ---> subCommentCommentDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new SubCommentCommentEntity();
        result.setId(dto.getId());
        result.setUserId(dto.getUserId());
        result.setText(dto.getText());
        result.setDate(dto.getDate());

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<SubCommentCommentDTO} to {@literal List<SubCommentCommentEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<SubCommentCommentEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<SubCommentCommentEntity> mapListDTOToListEntity(List<SubCommentCommentDTO> dtos) {
        List<SubCommentCommentEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> subCommentCommentDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(SubCommentCommentConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
