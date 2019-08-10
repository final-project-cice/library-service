package com.trl.library.repository;

import com.trl.library.repository.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AuthorEntity, Long> {

}
