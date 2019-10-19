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
    private List<EmailAuthorEntity> emailAuthorEntities;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberAuthorEntity> phoneNumberAuthorEntities;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressAuthorEntity> addressAuthorEntities;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @OneToMany(mappedBy = "authorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreAuthorEntity> genreAuthorEntities;

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

    public List<EmailAuthorEntity> getEmailAuthorEntities() {
        return emailAuthorEntities;
    }

    public void setEmailAuthorEntities(List<EmailAuthorEntity> emailAuthorEntities) {
        this.emailAuthorEntities = emailAuthorEntities;
    }

    public List<PhoneNumberAuthorEntity> getPhoneNumberAuthorEntities() {
        return phoneNumberAuthorEntities;
    }

    public void setPhoneNumberAuthorEntities(List<PhoneNumberAuthorEntity> phoneNumberAuthorEntities) {
        this.phoneNumberAuthorEntities = phoneNumberAuthorEntities;
    }

    public List<AddressAuthorEntity> getAddressAuthorEntities() {
        return addressAuthorEntities;
    }

    public void setAddressAuthorEntities(List<AddressAuthorEntity> addressAuthorEntities) {
        this.addressAuthorEntities = addressAuthorEntities;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<GenreAuthorEntity> getGenreAuthorEntities() {
        return genreAuthorEntities;
    }

    public void setGenreAuthorEntities(List<GenreAuthorEntity> genreAuthorEntities) {
        this.genreAuthorEntities = genreAuthorEntities;
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
                Objects.equals(emailAuthorEntities, that.emailAuthorEntities) &&
                Objects.equals(phoneNumberAuthorEntities, that.phoneNumberAuthorEntities) &&
                Objects.equals(addressAuthorEntities, that.addressAuthorEntities) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(genreAuthorEntities, that.genreAuthorEntities) &&
                Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailAuthorEntities, phoneNumberAuthorEntities, addressAuthorEntities, birthday, genreAuthorEntities, books);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailEntities=" + emailAuthorEntities +
                ", phoneNumberEntities=" + phoneNumberAuthorEntities +
                ", addressEntities=" + addressAuthorEntities +
                ", birthday=" + birthday +
                ", genreEntities=" + genreAuthorEntities +
                ", bookEntities=" + books +
                '}';
    }
}
