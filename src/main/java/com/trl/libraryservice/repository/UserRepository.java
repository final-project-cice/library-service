package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
