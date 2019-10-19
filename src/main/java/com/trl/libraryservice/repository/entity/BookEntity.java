package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "BookEntity")
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreBookEntity> genreBookEntities;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PublishingHouseEntity publishingHouse;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "path_file", nullable = false)
    private String pathFile;

    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentBookEntity> commentBookEntities;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> authors;

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

    public List<CommentBookEntity> getCommentBookEntities() {
        return commentBookEntities;
    }

    public void setCommentBookEntities(List<CommentBookEntity> commentBookEntities) {
        this.commentBookEntities = commentBookEntities;
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    public void addAuthor(AuthorEntity authorEntity) {
        this.authors.add(authorEntity);
        authorEntity.getBooks().add(this);
    }

    public void removeAuthor(AuthorEntity authorEntity) {
        this.authors.remove(authorEntity);
        authorEntity.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(genreBookEntities, that.genreBookEntities) &&
                Objects.equals(publishingHouse, that.publishingHouse) &&
                Objects.equals(publicationDate, that.publicationDate) &&
                Objects.equals(pathFile, that.pathFile) &&
                Objects.equals(commentBookEntities, that.commentBookEntities) &&
                Objects.equals(authors, that.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genreBookEntities, publishingHouse, publicationDate, pathFile, commentBookEntities, authors);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreBookEntities=" + genreBookEntities +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookEntities=" + commentBookEntities +
                ", authors=" + authors +
                '}';
    }
}
