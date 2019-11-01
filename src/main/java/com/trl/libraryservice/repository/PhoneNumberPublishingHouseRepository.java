package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.PhoneNumberPublishingHouseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal PhoneNumberPublishingHouseEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface PhoneNumberPublishingHouseRepository extends JpaRepository<PhoneNumberPublishingHouseEntity, Long> {

}

