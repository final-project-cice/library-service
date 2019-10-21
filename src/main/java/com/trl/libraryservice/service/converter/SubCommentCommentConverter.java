package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.SubCommentCommentDTO;
import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class SubCommentCommentConverter {

    private static final Logger LOG = LoggerFactory.getLogger(SubCommentCommentConverter.class);

    private SubCommentCommentConverter() { }

    public static SubCommentCommentDTO mapEntityToDTO(SubCommentCommentEntity entity) {
        SubCommentCommentDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> subCommentCommentEntity = " + entity + " ---> "
                + "subCommentCommentEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new SubCommentCommentDTO();
            result.setId(entity.getId());
            result.setUser(UserConverter.mapEntityToDTO(entity.getUser()));
            result.setText(entity.getText());
            result.setDate(entity.getDate());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setCommentBookDTO(CommentBookConverter.mapEntityToDTO(entity.getCommentBookEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<SubCommentCommentDTO> mapListEntityToListDTO(List<SubCommentCommentEntity> entities) {
        List<SubCommentCommentDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> subCommentCommentEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(SubCommentCommentConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static SubCommentCommentEntity mapDTOToEntity(SubCommentCommentDTO dto) {
        SubCommentCommentEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> subCommentCommentDTO = " + dto
                + " ---> subCommentCommentDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new SubCommentCommentEntity();
            result.setId(dto.getId());
            result.setUser(UserConverter.mapDTOToEntity(dto.getUser()));
            result.setText(dto.getText());
            result.setDate(dto.getDate());
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setCommentBookEntity(CommentBookConverter.mapDTOToEntity(dto.getCommentBookDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<SubCommentCommentEntity> mapListDTOToListEntity(List<SubCommentCommentDTO> dtos) {
        List<SubCommentCommentEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> subCommentCommentDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(SubCommentCommentConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
