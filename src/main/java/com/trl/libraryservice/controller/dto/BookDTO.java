package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.*;

/**
 * This class is designed to represent DTO object of address of author.
 *
 * @author Tsyupryk Roman
 */
public class BookDTO extends ResourceSupport {

    private Long bookId;
    private String name;
    private List<GenreBookDTO> genres = new ArrayList<>();
    private PublishingHouseDTO publishingHouse;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate publicationDate;

    private String pathFile;
    private List<CommentBookDTO> comments = new ArrayList<>();

    private Set<AuthorDTO> authors = new HashSet<>();

    public BookDTO() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GenreBookDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBookDTO> genres) {
        this.genres = genres;
    }

    public void addGenre(GenreBookDTO genre) {
        this.genres.add(genre);
        genre.setBook(this);
    }

    public void addGenres(List<GenreBookDTO> genreList) {
        genreList.forEach(this::addGenre);
    }

    public void removeGenre(GenreBookDTO genre) {
        this.genres.remove(genre);
        genre.setBook(null);
    }

    public PublishingHouseDTO getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseDTO publishingHouse) {
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

    public List<CommentBookDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentBookDTO> comments) {
        this.comments = comments;
    }

    public void addComment(CommentBookDTO comment) {
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void addComments(List<CommentBookDTO> commentList) {
        commentList.forEach(this::addComment);
    }

    public void removeComment(CommentBookDTO comment) {
        this.comments.remove(comment);
        comment.setBook(null);
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    public void addAuthor(AuthorDTO author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void addAuthors(Set<AuthorDTO> authorSet) {
        authorSet.forEach(this::addAuthor);
    }

    public void removeAuthor(AuthorDTO author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(bookId, bookDTO.bookId) &&
                Objects.equals(name, bookDTO.name) &&
                Objects.equals(genres, bookDTO.genres) &&
                Objects.equals(publishingHouse, bookDTO.publishingHouse) &&
                Objects.equals(publicationDate, bookDTO.publicationDate) &&
                Objects.equals(pathFile, bookDTO.pathFile) &&
                Objects.equals(comments, bookDTO.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, name, genres, publishingHouse, publicationDate, pathFile, comments);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + bookId +
                ", name='" + name + '\'' +
                ", genreBookDTOS=" + genres +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookDTOS=" + comments +
                '}';
    }
}
