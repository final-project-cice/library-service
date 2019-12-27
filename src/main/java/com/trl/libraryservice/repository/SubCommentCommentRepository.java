package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO sub_comment_comment (id, date_of_creation, text, user_id, comment_id) VALUES (:id, :date, :text, :userId, :commentId)", nativeQuery = true)
    void add(@Param("id") Long id, @Param("date") LocalDate date, @Param("text") String text, @Param("userId") Long userId, @Param("commentId") Long commentId);


    @Query(value = "SELECT e FROM SubCommentCommentEntity e WHERE e.comment.id=:commentId")
    List<SubCommentCommentEntity> findByCommentId(@Param("commentId") Long commentId);

    @Query(value = "SELECT e FROM SubCommentCommentEntity e WHERE e.comment.id=:commentId")
    Page<SubCommentCommentEntity> getPageOfSubCommentsByCommentId(@Param("commentId") Long commentId, Pageable pageable);


    @Transactional
    @Modifying
    @Query("DELETE FROM SubCommentCommentEntity e WHERE e.comment.id=:commentId")
    void deleteAllByCommentId(@Param("commentId") Long commentId);
}
