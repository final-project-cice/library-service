package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.CommentBookEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentBookRepository extends JpaRepository<CommentBookEntity, Long> {

}
