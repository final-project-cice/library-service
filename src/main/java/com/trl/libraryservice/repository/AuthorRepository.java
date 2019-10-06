package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
