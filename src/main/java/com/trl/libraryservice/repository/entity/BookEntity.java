package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AuthorEntity> authors;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private GenreEntity genre;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PublishingHouseEntity publishingHouse;

    @Column(name = "publicationDate", updatable = false, nullable = false)
    private LocalDate publicationDate;

    @Column(name = "pathFile", nullable = false)
    private String pathFile;

    public BookEntity() {
    }

    public BookEntity(String name, GenreEntity genre, PublishingHouseEntity publishingHouse, LocalDate publicationDate, String pathFile) {
        this.name = name;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.publicationDate = publicationDate;
        this.pathFile = pathFile;
    }

    public BookEntity(String name, Set<AuthorEntity> authors, GenreEntity genre, PublishingHouseEntity publishingHouse, LocalDate publicationDate, String pathFile) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.publicationDate = publicationDate;
        this.pathFile = pathFile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPathFile() {
        return pathFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                authors.equals(that.authors) &&
                genre.equals(that.genre) &&
                publishingHouse.equals(that.publishingHouse) &&
                publicationDate.equals(that.publicationDate) &&
                pathFile.equals(that.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genre, publishingHouse, publicationDate, pathFile);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genre=" + genre +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }

}
