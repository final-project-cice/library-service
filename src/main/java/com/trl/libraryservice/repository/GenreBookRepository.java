package com.trl.libraryservice.repository;


import com.trl.libraryservice.repository.entity.GenreBookEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBookEntity, Long> {

}