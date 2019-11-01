package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.AuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal AuthorEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
