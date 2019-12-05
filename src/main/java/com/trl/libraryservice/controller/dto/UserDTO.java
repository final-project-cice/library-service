package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is designed to represent DTO object of user.
 *
 * @author Tsyupryk Roman
 */
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) &&
                firstName.equals(userDTO.firstName) &&
                lastName.equals(userDTO.lastName) &&
                email.equals(userDTO.email) &&
                password.equals(userDTO.password) &&
                birthday.equals(userDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, birthday);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}
