package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.SubCommentCommentEntity;
import com.trl.libraryservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SubCommentCommentRepository extends JpaRepository<SubCommentCommentEntity, Long> {

    /**
     * @param id
     * @param user
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.user=:user where sc.id =:id", nativeQuery = true)
    void updateUser(@Param("id") Long id, @Param("user") UserEntity user);

    /**
     * @param id
     * @param text
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.text=:text where sc.id =:id", nativeQuery = true)
    void updateTest(@Param("id") Long id, @Param("text") String text);

    /**
     * @param id
     * @param date
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update SubCommentCommentEntity sc set sc.date=:date where sc.id =:id", nativeQuery = true)
    void updateDate(@Param("id") Long id, @Param("date") LocalDate date);


    /**
     * @param user
     * @return
     */
    List<SubCommentCommentEntity> findByUser(UserEntity user);

    /**
     * @param text
     * @return
     */
    List<SubCommentCommentEntity> findByText(String text);

    /**
     * @param date
     * @return
     */
    List<SubCommentCommentEntity> findByDate(LocalDate date);
}
