package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.AddressAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal AddressAuthorEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface AddressAuthorRepository extends JpaRepository<AddressAuthorEntity, Long> {

}
