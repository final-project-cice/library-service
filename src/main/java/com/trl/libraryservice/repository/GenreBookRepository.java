package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.GenreBookEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal GenreBookEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface GenreBookRepository extends JpaRepository<GenreBookEntity, Long> {

}
