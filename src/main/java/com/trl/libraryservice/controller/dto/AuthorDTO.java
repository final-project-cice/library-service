package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<EmailAuthorDTO> emails;
    private List<PhoneNumberAuthorDTO> phoneNumbers;
    private List<AddressAuthorDTO> addresses;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private List<GenreAuthorDTO> genres;
    private Set<BookDTO> books;

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

    public List<EmailAuthorDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailAuthorDTO> emails) {
        this.emails = emails;
    }

    public List<PhoneNumberAuthorDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberAuthorDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<AddressAuthorDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressAuthorDTO> addresses) {
        this.addresses = addresses;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<GenreAuthorDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreAuthorDTO> genres) {
        this.genres = genres;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }

    public void addBook(BookDTO bookDTO) {
        this.books.add(bookDTO);
        bookDTO.getAuthors().add(this);
    }

    public void removeBook(BookDTO bookDTO) {
        this.books.remove(bookDTO);
        bookDTO.getAuthors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(id, authorDTO.id) &&
                Objects.equals(firstName, authorDTO.firstName) &&
                Objects.equals(lastName, authorDTO.lastName) &&
                Objects.equals(emails, authorDTO.emails) &&
                Objects.equals(phoneNumbers, authorDTO.phoneNumbers) &&
                Objects.equals(addresses, authorDTO.addresses) &&
                Objects.equals(birthday, authorDTO.birthday) &&
                Objects.equals(genres, authorDTO.genres) &&
                // TODO: Check it, a cyclic call will be triggered here.
                Objects.equals(books, authorDTO.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emails, phoneNumbers, addresses,
                birthday, genres,
                // TODO: Check it, a cyclic call will be triggered here.
                books);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAuthorDTOS=" + emails +
                ", phoneNumberAuthorDTOS=" + phoneNumbers +
                ", addressAuthorDTOS=" + addresses +
                ", birthday=" + birthday +
                ", genreAuthorDTOS=" + genres +
                // TODO: Check it, a cyclic call will be triggered here.
                ", bookDTOS=" + books +
                '}';
    }
}
