package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * This interface is designed to support JPA for {@literal BookEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE BookEntity e SET e.name=:name WHERE e.id =:id")
    void updateName(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE BookEntity e SET e.publicationDate=:publicationDate WHERE e.id =:id")
    void updatePublicationDate(@Param("id") Long id, @Param("publicationDate") LocalDate publicationDate);
}
