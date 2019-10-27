package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.CommentBookEntity;
import com.trl.libraryservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CommentBookRepository extends JpaRepository<CommentBookEntity, Long> {

    /**
     * @param id
     * @param user
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update CommentBookEntity cb set cb.user=:user where cb.id =:id", nativeQuery = true)
    void updateUser(@Param("id") Long id, @Param("user") UserEntity user);

    /**
     * @param id
     * @param text
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update CommentBookEntity cb set cb.text=:text where cb.id =:id", nativeQuery = true)
    void updateText(@Param("id") Long id, @Param("text") String text);

    /**
     * @param id
     * @param date
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update CommentBookEntity cb set cb.date=:date where cb.id =:id", nativeQuery = true)
    void updateDate(@Param("id") Long id, @Param("date") LocalDate date);


    /**
     * @param user
     * @return
     */
    List<CommentBookEntity> findByUser(UserEntity user);

    /**
     * @param text
     * @return
     */
    List<CommentBookEntity> findByText(String text);

    /**
     * @param date
     * @return
     */
    List<CommentBookEntity> findByDate(LocalDate date);
}
