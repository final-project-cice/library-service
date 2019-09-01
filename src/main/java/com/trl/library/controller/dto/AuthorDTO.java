package com.trl.library.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AddressDTO> address;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private Set<GenreDTO> genres;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String lastName, String email, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public AuthorDTO(Long id, String firstName, String lastName, String email, Set<AddressDTO> address, LocalDate birthday, Set<GenreDTO> genres) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<AddressDTO> getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return id.equals(authorDTO.id) &&
                firstName.equals(authorDTO.firstName) &&
                lastName.equals(authorDTO.lastName) &&
                email.equals(authorDTO.email) &&
                address.equals(authorDTO.address) &&
                birthday.equals(authorDTO.birthday) &&
                genres.equals(authorDTO.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address, birthday, genres);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", birthday=" + birthday +
                ", genres=" + genres +
                '}';
    }

}
