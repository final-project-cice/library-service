package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.EmailAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthorRepository extends JpaRepository<EmailAuthorEntity, Long> {

}
