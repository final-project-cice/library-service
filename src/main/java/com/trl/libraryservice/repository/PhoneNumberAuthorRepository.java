package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.PhoneNumberAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal PhoneNumberAuthorEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface PhoneNumberAuthorRepository extends JpaRepository<PhoneNumberAuthorEntity, Long> {

}
