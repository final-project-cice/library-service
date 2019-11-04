package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.CommentBookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface is designed to support JPA for {@literal CommentBookEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface CommentBookRepository extends JpaRepository<CommentBookEntity, Long> {

    /**
     * @param id
     * @param userId
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update CommentBookEntity cb set cb.userId=:userId where cb.id =:id", nativeQuery = true)
    void updateUserId(@Param("id") Long id, @Param("userId") Long userId);

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
     * @param userId
     * @return
     */
    List<CommentBookEntity> findByUserId(Long userId);

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
