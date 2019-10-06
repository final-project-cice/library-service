package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
