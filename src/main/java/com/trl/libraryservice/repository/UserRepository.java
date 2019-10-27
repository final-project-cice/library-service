package com.trl.libraryservice.repository;

import com.trl.libraryservice.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * @param id
     * @param firstName
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.firstName=:firstName where u.id =:id", nativeQuery = true)
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    /**
     * @param id
     * @param lastName
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.lastName=:lastName where u.id =:id", nativeQuery = true)
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    /**
     * @param id
     * @param email
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.email=:email where u.id =:id", nativeQuery = true)
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    /**
     * @param id
     * @param birthday
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity u set u.birthday=:birthday where u.id =:id", nativeQuery = true)
    void updateBirthday(@Param("id") Long id, @Param("birthday") LocalDate birthday);


    /**
     * @param firstName
     * @return
     */
    List<UserEntity> findByFirstName(String firstName);

    /**
     * @param firstName
     * @return
     */
    List<UserEntity> findByLastName(String firstName);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * @param email
     * @return
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * @param birthday
     * @return
     */
    List<UserEntity> findByBirthday(LocalDate birthday);
}
