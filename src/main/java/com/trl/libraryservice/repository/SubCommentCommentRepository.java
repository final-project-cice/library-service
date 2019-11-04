package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface is designed to support JPA for {@literal SubCommentCommentEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface SubCommentCommentRepository extends JpaRepository<SubCommentCommentEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.userId=:userId where sc.id =:id", nativeQuery = true)
    void updateUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.text=:text where sc.id =:id", nativeQuery = true)
    void updateText(@Param("id") Long id, @Param("text") String text);

    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.date=:date where sc.id =:id", nativeQuery = true)
    void updateDate(@Param("id") Long id, @Param("date") LocalDate date);


    List<SubCommentCommentEntity> findByUserId(Long userId);

    List<SubCommentCommentEntity> findByText(String text);

    List<SubCommentCommentEntity> findByDate(LocalDate date);
}
