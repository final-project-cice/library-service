package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.repository.entity.CommentBookEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal CommentBookEntity} to {@literal CommentBookDTO} and vice versa.
 * And also, this class is designed to convert {@literal List<CommentBookEntity>} to {@literal List<CommentBookDTO>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
public final class CommentBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    private CommentBookConverter() {
    }

    /**
     * Convert {@literal CommentBookEntity} to {@literal CommentBookDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal CommentBookDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public static CommentBookDTO mapEntityToDTO(CommentBookEntity entity) {
        CommentBookDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> commentBookEntity = " + entity
                + " ---> commentBookEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new CommentBookDTO();
        result.setId(entity.getId());
        result.setUserId(entity.getUserId());
        result.setText(entity.getText());
        result.setDate(entity.getDate());
        result.addSubComments(SubCommentCommentConverter.mapListEntityToListDTO(entity.getSubComments()));

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<CommentBookEntity>} to {@literal List<CommentBookDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal List<CommentBookDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static List<CommentBookDTO> mapListEntityToListDTO(List<CommentBookEntity> entities) {
        List<CommentBookDTO> resultList;

        if (entities == null) {
            LOG.debug("************ mapListEntityToListDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListEntityToListDTO() ---> commentBookEntityList = " + entities);

        resultList = entities.parallelStream()
                .map(CommentBookConverter::mapEntityToDTO)
                .collect(Collectors.toList());

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * Convert {@literal Page<CommentBookEntity>} to {@literal Page<CommentBookDTO>}.
     *
     * @param entities must not be {@literal null}.
     * @return the {@literal Page<CommentBookDTO>}.
     * @throws IllegalArgumentException in case the given {@code entities} is {@literal null}.
     */
    public static Page<CommentBookDTO> mapPageEntityToPageDTO(Page<CommentBookEntity> entities) {
        Page<CommentBookDTO> resultPage;

        if (entities == null) {
            LOG.debug("************ mapPageEntityToPageDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapPageEntityToPageDTO() ---> commentBookEntityPage = " + entities);

        resultPage = entities.map(CommentBookConverter::mapEntityToDTO);

        LOG.debug("************ mapPageEntityToPageDTO() ---> resultList = " + resultPage);

        return resultPage;
    }

    /**
     * Convert {@literal CommentBookDTO} to {@literal CommentBookEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal CommentBookEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public static CommentBookEntity mapDTOToEntity(CommentBookDTO dto) {
        CommentBookEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> commentBookDTO = " + dto
                + " ---> commentBookDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new CommentBookEntity();
        result.setId(dto.getId());
        result.setUserId(dto.getUserId());
        result.setText(dto.getText());
        result.setDate(dto.getDate());
        result.addSubComments(SubCommentCommentConverter.mapListDTOToListEntity(dto.getSubComments()));

        LOG.debug("************ mapDTOToEntity() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal List<CommentBookDTO} to {@literal List<CommentBookEntity>}.
     *
     * @param dtos must not be {@literal null}.
     * @return the {@literal List<CommentBookEntity>}.
     * @throws IllegalArgumentException in case the given {@code dtos} is {@literal null}.
     */
    public static List<CommentBookEntity> mapListDTOToListEntity(List<CommentBookDTO> dtos) {
        List<CommentBookEntity> resultList;

        if (dtos == null) {
            LOG.debug("************ mapListDTOToListEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapListDTOToListEntity() ---> commentBookDTOList = " + dtos);

        resultList = dtos.parallelStream()
                .map(CommentBookConverter::mapDTOToEntity)
                .collect(Collectors.toList());

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}