package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.CommentBookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This interface is designed to support JPA for {@literal CommentBookEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface CommentBookRepository extends JpaRepository<CommentBookEntity, Long> {

    // TODO: Thing of how to make all query using a JPQL.

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment_book (date, text, user_id, book_id) VALUES (:date, :text, :userId, :bookId)", nativeQuery = true)
    void add(@Param("date") LocalDate date,
             @Param("text") String text, @Param("userId") Long userId, @Param("bookId") Long bookId);


    @Query(value = "SELECT * FROM comment_book cb WHERE book_id=:bookId", nativeQuery = true)
    List<CommentBookEntity> findByBookId(@Param("bookId") Long bookId);

    @Query(value = "SELECT * FROM comment_book cb WHERE book_id=:bookId AND id=:commentId", nativeQuery = true)
    Optional<CommentBookEntity> findByBookIdAndCommentId(@Param("bookId") Long bookId, @Param("commentId") Long commentId);

    @Query(value = "DELETE FROM comment_book WHERE book_id=:bookId AND id=:commentId", nativeQuery = true)
    void deleteByBookIdAndByCommentId(@Param("bookId") Long bookId, @Param("commentId") Long commentId);

    @Query(value = "DELETE FROM comment_book WHERE book_id=:bookId", nativeQuery = true)
    void deleteAllByBookId(@Param("bookId") Long bookId);
}
