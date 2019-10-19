package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<EmailAuthorDTO> emailAuthorDTOS;
    private List<PhoneNumberAuthorDTO> phoneNumberAuthorDTOS;
    private List<AddressAuthorDTO> addressAuthorDTOS;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private List<GenreAuthorDTO> genreAuthorDTOS;
    private List<BookDTO> bookDTOS;

    public AuthorDTO() { }

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

    public List<EmailAuthorDTO> getEmailAuthorDTOS() {
        return emailAuthorDTOS;
    }

    public void setEmailAuthorDTOS(List<EmailAuthorDTO> emailAuthorDTOS) {
        this.emailAuthorDTOS = emailAuthorDTOS;
    }

    public List<PhoneNumberAuthorDTO> getPhoneNumberAuthorDTOS() {
        return phoneNumberAuthorDTOS;
    }

    public void setPhoneNumberAuthorDTOS(List<PhoneNumberAuthorDTO> phoneNumberAuthorDTOS) {
        this.phoneNumberAuthorDTOS = phoneNumberAuthorDTOS;
    }

    public List<AddressAuthorDTO> getAddressAuthorDTOS() {
        return addressAuthorDTOS;
    }

    public void setAddressAuthorDTOS(List<AddressAuthorDTO> addressAuthorDTOS) {
        this.addressAuthorDTOS = addressAuthorDTOS;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<GenreAuthorDTO> getGenreAuthorDTOS() {
        return genreAuthorDTOS;
    }

    public void setGenreAuthorDTOS(List<GenreAuthorDTO> genreAuthorDTOS) {
        this.genreAuthorDTOS = genreAuthorDTOS;
    }

    public List<BookDTO> getBookDTOS() {
        return bookDTOS;
    }

    public void setBookDTOS(List<BookDTO> bookDTOS) {
        this.bookDTOS = bookDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(id, authorDTO.id) &&
                Objects.equals(firstName, authorDTO.firstName) &&
                Objects.equals(lastName, authorDTO.lastName) &&
                Objects.equals(emailAuthorDTOS, authorDTO.emailAuthorDTOS) &&
                Objects.equals(phoneNumberAuthorDTOS, authorDTO.phoneNumberAuthorDTOS) &&
                Objects.equals(addressAuthorDTOS, authorDTO.addressAuthorDTOS) &&
                Objects.equals(birthday, authorDTO.birthday) &&
                Objects.equals(genreAuthorDTOS, authorDTO.genreAuthorDTOS) &&
                Objects.equals(bookDTOS, authorDTO.bookDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailAuthorDTOS, phoneNumberAuthorDTOS, addressAuthorDTOS, birthday, genreAuthorDTOS, bookDTOS);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAuthorDTOS=" + emailAuthorDTOS +
                ", phoneNumberAuthorDTOS=" + phoneNumberAuthorDTOS +
                ", addressAuthorDTOS=" + addressAuthorDTOS +
                ", birthday=" + birthday +
                ", genreAuthorDTOS=" + genreAuthorDTOS +
                ", bookDTOS=" + bookDTOS +
                '}';
    }
}
