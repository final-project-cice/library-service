package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.EmailPublishingHouseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is designed to support JPA for {@literal EmailPublishingHouseEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface EmailPublishingHouseRepository extends JpaRepository<EmailPublishingHouseEntity, Long> {

}
