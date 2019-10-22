package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "AuthorEntity")
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailAuthorEntity> emails;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberAuthorEntity> phoneNumbers;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressAuthorEntity> addresses;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreAuthorEntity> genres;

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
    private Set<BookEntity> books;

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

    public List<PhoneNumberAuthorEntity> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberAuthorEntity> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<AddressAuthorEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressAuthorEntity> addresses) {
        this.addresses = addresses;
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

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    public void addBook(BookEntity bookEntity) {
        this.books.add(bookEntity);
        bookEntity.getAuthors().add(this);
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
                Objects.equals(genres, that.genres) &&
                // TODO: Check it, a cyclic call will be triggered here.
                Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emails, phoneNumbers,
                addresses, birthday, genres,
                // TODO: Check it, a cyclic call will be triggered here.
                books);
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
                // TODO: Check it, a cyclic call will be triggered here.
                ", bookEntities=" + books +
                '}';
    }
}
