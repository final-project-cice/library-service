package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.GenreAuthorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreAuthorRepository extends JpaRepository<GenreAuthorEntity, Long> {

}
