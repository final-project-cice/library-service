package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.GenreAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal GenreAuthorEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface GenreAuthorRepository extends JpaRepository<GenreAuthorEntity, Long> {

}
