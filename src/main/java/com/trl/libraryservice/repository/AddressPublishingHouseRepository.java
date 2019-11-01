package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.AddressPublishingHouseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal AddressPublishingHouseEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface AddressPublishingHouseRepository extends JpaRepository<AddressPublishingHouseEntity, Long> {

}
