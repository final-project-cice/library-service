package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This interface is designed to support JPA for {@literal UserEntity}.
 *
 * @author Tsyupryk Roman
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.firstName=:firstName where u.id =:id", nativeQuery = true)
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.lastName=:lastName where u.id =:id", nativeQuery = true)
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.email=:email where u.id =:id", nativeQuery = true)
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.birthday=:birthday where u.id =:id", nativeQuery = true)
    void updateBirthday(@Param("id") Long id, @Param("birthday") LocalDate birthday);


    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String firstName);

    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByBirthday(LocalDate birthday);
}
