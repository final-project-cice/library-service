package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookDTO {

    private Long id;
    private String name;
    private List<GenreBookDTO> genres;
    private PublishingHouseDTO publishingHouse;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate publicationDate;

    private String pathFile;
    private List<CommentBookDTO> comments;

    private Set<AuthorDTO> authors;

    public BookDTO() { }

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

    public List<GenreBookDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreBookDTO> genres) {
        this.genres = genres;
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

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    public void addAuthor(AuthorDTO authorDTO) {
        this.authors.add(authorDTO);
        authorDTO.getBooks().add(this);
    }

    public void removeAuthor(AuthorDTO authorDTO) {
        this.authors.remove(authorDTO);
        authorDTO.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) &&
                Objects.equals(name, bookDTO.name) &&
                Objects.equals(genres, bookDTO.genres) &&
                Objects.equals(publishingHouse, bookDTO.publishingHouse) &&
                Objects.equals(publicationDate, bookDTO.publicationDate) &&
                Objects.equals(pathFile, bookDTO.pathFile) &&
                Objects.equals(comments, bookDTO.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genres, publishingHouse, publicationDate, pathFile, comments);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreBookDTOS=" + genres +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookDTOS=" + comments +
                '}';
    }
}
