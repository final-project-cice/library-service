package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface is designed to support JPA for {@literal SubCommentCommentEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface SubCommentCommentRepository extends JpaRepository<SubCommentCommentEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO SubCommentCommenEntity (userId, text, date, commentId) VALUES (:userId, :text, :date, :commentId)", nativeQuery = true)
    void add(@Param("userId") Long userId, @Param("text") String text,
             @Param("date") LocalDate date, @Param("commentId") Long commentId);


    @Query(value = "SELECT e FROM SubCommentCommentEntity e WHERE e.comment.id=:commentId")
    List<SubCommentCommentEntity> findByCommentId(@Param("commentId") Long commentId);


    @Transactional
    @Modifying
    @Query("DELETE FROM SubCommentCommentEntity e WHERE e.comment.id=:commentId")
    void deleteAllByCommentId(@Param("commentId") Long commentId);
}
