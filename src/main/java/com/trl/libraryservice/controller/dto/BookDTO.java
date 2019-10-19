package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookDTO {

    private Long id;
    private String name;
    private List<AuthorDTO> authorDTOS;
    private List<GenreBookDTO> genreBookDTOS;
    private PublishingHouseDTO publishingHouse;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate publicationDate;

    private String pathFile;
    private List<CommentBookDTO> commentBookDTOS;

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

    public List<AuthorDTO> getAuthorDTOS() {
        return authorDTOS;
    }

    public void setAuthorDTOS(List<AuthorDTO> authorDTOS) {
        this.authorDTOS = authorDTOS;
    }

    public List<GenreBookDTO> getGenreBookDTOS() {
        return genreBookDTOS;
    }

    public void setGenreBookDTOS(List<GenreBookDTO> genreBookDTOS) {
        this.genreBookDTOS = genreBookDTOS;
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

    public List<CommentBookDTO> getCommentBookDTOS() {
        return commentBookDTOS;
    }

    public void setCommentBookDTOS(List<CommentBookDTO> commentBookDTOS) {
        this.commentBookDTOS = commentBookDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) &&
                Objects.equals(name, bookDTO.name) &&
                Objects.equals(authorDTOS, bookDTO.authorDTOS) &&
                Objects.equals(genreBookDTOS, bookDTO.genreBookDTOS) &&
                Objects.equals(publishingHouse, bookDTO.publishingHouse) &&
                Objects.equals(publicationDate, bookDTO.publicationDate) &&
                Objects.equals(pathFile, bookDTO.pathFile) &&
                Objects.equals(commentBookDTOS, bookDTO.commentBookDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorDTOS, genreBookDTOS, publishingHouse, publicationDate, pathFile, commentBookDTOS);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorDTOS=" + authorDTOS +
                ", genreBookDTOS=" + genreBookDTOS +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookDTOS=" + commentBookDTOS +
                '}';
    }
}
