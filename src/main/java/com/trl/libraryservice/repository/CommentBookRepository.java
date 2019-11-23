package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.BookEntity;
import com.trl.libraryservice.repository.entity.CommentBookEntity;

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
 * This interface is designed to support JPA for {@literal CommentBookEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface CommentBookRepository extends JpaRepository<CommentBookEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment_book (id, date, text, user_id, book_id) VALUES (:id, :date, :text, :userId, :bookId)", nativeQuery = true)
    void add(@Param("id") Long id, @Param("date") LocalDate date, @Param("text") String text, @Param("userId") Long userId, @Param("bookId") Long bookId);


    @Query(value = "SELECT e FROM CommentBookEntity e WHERE e.userId=:userId  AND e.text=:text AND e.date=:date AND e.book=:book")
    CommentBookEntity findComment(@Param("userId") Long userId, @Param("text") String text, @Param("date") LocalDate date, @Param("book") BookEntity book);


    @Query(value = "SELECT cb FROM CommentBookEntity cb WHERE cb.book.id=:bookId")
    List<CommentBookEntity> findByBookId(@Param("bookId") Long bookId);

    @Query(value = "SELECT cb FROM CommentBookEntity cb WHERE cb.book.id=:bookId")
    Page<CommentBookEntity> getPageByBookId(@Param("bookId") Long bookId, Pageable pageable);


    @Transactional
    @Modifying
    @Query("DELETE FROM CommentBookEntity cb WHERE cb.book.id=:bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);
}
