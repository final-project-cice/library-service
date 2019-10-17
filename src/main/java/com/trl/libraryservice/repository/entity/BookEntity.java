package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(name = "BookEntity")
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "author")
    private List<AuthorEntity> authors;

    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreBookEntity> genreBookEntities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PublishingHouseEntity publishingHouse;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "path_file", nullable = false)
    private String pathFile;

    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public List<GenreBookEntity> getGenreBookEntities() {
        return genreBookEntities;
    }

    public void setGenreBookEntities(List<GenreBookEntity> genreBookEntities) {
        this.genreBookEntities = genreBookEntities;
    }

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseEntity publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(genreBookEntities, that.genreBookEntities) &&
                Objects.equals(publishingHouse, that.publishingHouse) &&
                Objects.equals(publicationDate, that.publicationDate) &&
                Objects.equals(pathFile, that.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genreBookEntities, publishingHouse, publicationDate, pathFile);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorEntities=" + authors +
                ", genreEntities=" + genreBookEntities +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }

}
