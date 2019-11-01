package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.EmailAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal EmailAuthorEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface EmailAuthorRepository extends JpaRepository<EmailAuthorEntity, Long> {

}
