package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.*;

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<EmailAuthorDTO> emails = new ArrayList<>();
    private List<PhoneNumberAuthorDTO> phoneNumbers = new ArrayList<>();
    private List<AddressAuthorDTO> addresses = new ArrayList<>();

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private List<GenreAuthorDTO> genres = new ArrayList<>();

    @JsonIgnore
    private Set<BookDTO> books = new HashSet<>();

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

    public void addEmail(EmailAuthorDTO email) {
        this.emails.add(email);
        email.setAuthor(this);
    }

    public void addEmails(List<EmailAuthorDTO> emailList) {
        emailList.forEach(this::addEmail);
    }

    public void removeEmail(EmailAuthorDTO email) {
        this.emails.remove(email);
        email.setAuthor(null);
    }

    public List<PhoneNumberAuthorDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberAuthorDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumberAuthorDTO phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        phoneNumber.setAuthor(this);
    }

    public void addPhoneNumbers(List<PhoneNumberAuthorDTO> phoneNumberList) {
        phoneNumberList.forEach(this::addPhoneNumber);
    }

    public void removePhoneNumber(PhoneNumberAuthorDTO phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
        phoneNumber.setAuthor(null);
    }

    public List<AddressAuthorDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressAuthorDTO> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AddressAuthorDTO address) {
        this.addresses.add(address);
        address.setAuthor(this);
    }

    public void addAddresses(List<AddressAuthorDTO> addressList) {
        addressList.forEach(this::addAddress);
    }

    public void removeAddress(AddressAuthorDTO address) {
        this.addresses.remove(address);
        address.setAuthor(null);
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

    public void addGenre(GenreAuthorDTO genre) {
        this.genres.add(genre);
        genre.setAuthor(this);
    }

    public void addGenres(List<GenreAuthorDTO> genreList) {
        genreList.forEach(this::addGenre);
    }

    public void removeGenre(GenreAuthorDTO genre) {
        this.genres.remove(genre);
        genre.setAuthor(null);
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }

    public void addBook(BookDTO book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void addBooks(Set<BookDTO> bookList) {
        bookList.forEach(this::addBook);
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
                Objects.equals(genres, authorDTO.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emails, phoneNumbers, addresses, birthday, genres);
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
                '}';
    }
}
