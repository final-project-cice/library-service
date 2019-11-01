package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.PublishingHouseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal PublishingHouseEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface PublishingHouseRepository extends JpaRepository<PublishingHouseEntity, Long> {

}
