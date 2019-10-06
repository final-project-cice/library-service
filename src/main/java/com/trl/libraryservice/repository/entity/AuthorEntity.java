package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmailEntity> emails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhoneNumberEntity> phoneNumbers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AddressEntity> address;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GenreEntity> genres;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookEntity> books;

    public AuthorEntity() {
    }

    public AuthorEntity(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public AuthorEntity(String firstName, String lastName, Set<EmailEntity> emails, Set<PhoneNumberEntity> phoneNumbers, Set<AddressEntity> address, LocalDate birthday, Set<GenreEntity> genres, Set<BookEntity> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.birthday = birthday;
        this.genres = genres;
        this.books = books;
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

    public Set<EmailEntity> getEmails() {
        return emails;
    }

    public Set<PhoneNumberEntity> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Set<AddressEntity> getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return id.equals(that.id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                emails.equals(that.emails) &&
                phoneNumbers.equals(that.phoneNumbers) &&
                address.equals(that.address) &&
                birthday.equals(that.birthday) &&
                genres.equals(that.genres) &&
                books.equals(that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emails, phoneNumbers, address, birthday, genres, books);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emails=" + emails +
                ", phoneNumbers=" + phoneNumbers +
                ", address=" + address +
                ", birthday=" + birthday +
                ", genres=" + genres +
                ", books=" + books +
                '}';
    }

}
