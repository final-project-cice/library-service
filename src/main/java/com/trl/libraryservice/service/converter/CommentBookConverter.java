package com.trl.libraryservice.service.converter;

import com.trl.libraryservice.controller.dto.CommentBookDTO;
import com.trl.libraryservice.repository.entity.CommentBookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public final class CommentBookConverter {

    private static final Logger LOG = LoggerFactory.getLogger(CommentBookConverter.class);

    private CommentBookConverter() { }

    public static CommentBookDTO mapEntityToDTO(CommentBookEntity entity) {
        CommentBookDTO result = null;

        LOG.debug("************ mapEntityToDTO() ---> commentBookEntity = " + entity + " ---> "
                + "commentBookEntity.getClass().getSimpleName() = "
                + (entity != null ? entity.getClass().getSimpleName() : "null"));

        if (entity != null) {
            result = new CommentBookDTO();
            result.setId(entity.getId());
            result.setUser(UserConverter.mapEntityToDTO(entity.getUser()));
            result.setText(entity.getText());
            result.setDate(entity.getDate());
            result.setSubComments(SubCommentCommentConverter.mapListEntityToListDTO(entity.getSubComments()));
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookDTO(BookConverter.mapEntityToDTO(entity.getBookEntity()));
        }

        LOG.debug("************ mapEntityToDTO() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param entities
     * @return
     */
    public static List<CommentBookDTO> mapListEntityToListDTO(List<CommentBookEntity> entities) {
        List<CommentBookDTO> resultList = null;

        LOG.debug("************ mapListEntityToListDTO() ---> commentBookEntityList = " + entities);

        if (entities != null) {
            resultList = entities.parallelStream()
                    .map(CommentBookConverter::mapEntityToDTO)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListEntityToListDTO() ---> resultList = " + resultList);

        return resultList;
    }

    /**
     * @param dto
     * @return
     */
    public static CommentBookEntity mapDTOToEntity(CommentBookDTO dto) {
        CommentBookEntity result = null;

        LOG.debug("************ mapDTOToEntity() ---> commentBookDTO = " + dto
                + " ---> commentBookDTO.getClass().getSimpleName() = "
                + (dto != null ? dto.getClass().getSimpleName() : "null"));

        if (dto != null) {
            result = new CommentBookEntity();
            result.setId(dto.getId());
            result.setUser(UserConverter.mapDTOToEntity(dto.getUser()));
            result.setText(dto.getText());
            result.setDate(dto.getDate());
            result.setSubComments(SubCommentCommentConverter.mapListDTOToListEntity(dto.getSubComments()));
            // TODO: Check it, a cyclic call will be triggered here.
//            result.setBookEntity(BookConverter.mapDTOToEntity(dto.getBookDTO()));
        }

        LOG.debug("************ mapDTOToEntity() ---> result = " + result + " ---> result.getClass().getSimpleName() = "
                + (result != null ? result.getClass().getSimpleName() : "null"));

        return result;
    }

    /**
     * @param dtos
     * @return
     */
    public static List<CommentBookEntity> mapListDTOToListEntity(List<CommentBookDTO> dtos) {
        List<CommentBookEntity> resultList = null;

        LOG.debug("************ mapListDTOToListEntity() ---> commentBookDTOList = " + dtos);

        if (dtos != null) {
            resultList = dtos.stream()
                    .map(CommentBookConverter::mapDTOToEntity)
                    .collect(Collectors.toList());
        }

        LOG.debug("************ mapListDTOToListEntity() ---> resultList = " + resultList);

        return resultList;
    }
}
