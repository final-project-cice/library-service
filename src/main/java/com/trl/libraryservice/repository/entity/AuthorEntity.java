package com.trl.libraryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * This class is designed to represent Entity object of author.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "AuthorEntity")
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailAuthorEntity> emails = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberAuthorEntity> phoneNumbers = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressAuthorEntity> addresses = new ArrayList<>();

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreAuthorEntity> genres = new ArrayList<>();

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> books = new HashSet<>();

    public AuthorEntity() {
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

    public List<EmailAuthorEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailAuthorEntity> emails) {
        this.emails = emails;
    }

    public void addEmail(EmailAuthorEntity email) {
        this.emails.add(email);
        email.setAuthor(this);
    }

    public void addEmails(List<EmailAuthorEntity> emailList) {
        emailList.forEach(this::addEmail);
    }

    public void removeEmail(EmailAuthorEntity email) {
        this.emails.remove(email);
        email.setAuthor(null);
    }

    public List<PhoneNumberAuthorEntity> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberAuthorEntity> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumberAuthorEntity phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        phoneNumber.setAuthor(this);
    }

    public void addPhoneNumbers(List<PhoneNumberAuthorEntity> phoneNumberList) {
        phoneNumberList.forEach(this::addPhoneNumber);
    }

    public void removePhoneNumber(PhoneNumberAuthorEntity phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
        phoneNumber.setAuthor(null);
    }

    public List<AddressAuthorEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressAuthorEntity> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AddressAuthorEntity address) {
        this.addresses.add(address);
        address.setAuthor(this);
    }

    public void addAddresses(List<AddressAuthorEntity> addressList) {
        addressList.forEach(this::addAddress);
    }

    public void removeAddress(AddressAuthorEntity address) {
        this.addresses.remove(address);
        address.setAuthor(null);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<GenreAuthorEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreAuthorEntity> genres) {
        this.genres = genres;
    }

    public void addGenre(GenreAuthorEntity genre) {
        this.genres.add(genre);
        genre.setAuthor(this);
    }

    public void addGenres(List<GenreAuthorEntity> genreList) {
        genreList.forEach(this::addGenre);
    }

    public void removeGenre(GenreAuthorEntity genre) {
        this.genres.remove(genre);
        genre.setAuthor(null);
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    public void addBook(BookEntity book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void addBooks(Set<BookEntity> bookSet) {
        bookSet.forEach(this::addBook);
    }

    public void removeBook(BookEntity bookEntity) {
        this.books.remove(bookEntity);
        bookEntity.getAuthors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(emails, that.emails) &&
                Objects.equals(phoneNumbers, that.phoneNumbers) &&
                Objects.equals(addresses, that.addresses) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emails, phoneNumbers, addresses, birthday, genres);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailEntities=" + emails +
                ", phoneNumberEntities=" + phoneNumbers +
                ", addressEntities=" + addresses +
                ", birthday=" + birthday +
                ", genreEntities=" + genres +
                '}';
    }
}
