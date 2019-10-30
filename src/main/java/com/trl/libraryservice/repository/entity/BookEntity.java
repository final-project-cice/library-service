package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity(name = "BookEntity")
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenreBookEntity> genres = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PublishingHouseEntity publishingHouse;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "path_file", nullable = false)
    private String pathFile;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentBookEntity> comments = new ArrayList<>();

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
    private Set<AuthorEntity> authors = new HashSet<>();

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

    public List<GenreBookEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBookEntity> genres) {
        this.genres = genres;
    }

    public void addGenre(GenreBookEntity genre) {
        this.genres.add(genre);
        genre.setBook(this);
    }

    public void addGenres(List<GenreBookEntity> genreList) {
        genreList.forEach(this::addGenre);
    }

    public void removeGenre(GenreBookEntity genre) {
        this.genres.remove(genre);
        genre.setBook(null);
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

    public List<CommentBookEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentBookEntity> comments) {
        this.comments = comments;
    }

    public void addComment(CommentBookEntity comment) {
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void addComments(List<CommentBookEntity> commentList) {
        commentList.forEach(this::addComment);
    }

    public void removeComment(CommentBookEntity comment) {
        this.comments.remove(comment);
        comment.setBook(null);
    }

    public Set<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorEntity> authors) {
        this.authors = authors;
    }

    public void addAuthor(AuthorEntity author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void addAuthors(Set<AuthorEntity> authorSet) {
        authorSet.forEach(this::addAuthor);
    }

    public void removeAuthor(AuthorEntity author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(genres, that.genres) &&
                Objects.equals(publishingHouse, that.publishingHouse) &&
                Objects.equals(publicationDate, that.publicationDate) &&
                Objects.equals(pathFile, that.pathFile) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genres, publishingHouse, publicationDate, pathFile, comments);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreBookEntities=" + genres +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookEntities=" + comments +
                '}';
    }
}
