package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookDTO {

    private Long id;
    private String name;
    private List<GenreBookDTO> genreBookDTOS;
    private PublishingHouseDTO publishingHouseDTO;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate publicationDate;

    private String pathFile;
    private List<CommentBookDTO> commentBookDTOS;

    private Set<AuthorDTO> authorDTOS;

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

    public List<GenreBookDTO> getGenreBookDTOS() {
        return genreBookDTOS;
    }

    public void setGenreBookDTOS(List<GenreBookDTO> genreBookDTOS) {
        this.genreBookDTOS = genreBookDTOS;
    }

    public PublishingHouseDTO getPublishingHouseDTO() {
        return publishingHouseDTO;
    }

    public void setPublishingHouseDTO(PublishingHouseDTO publishingHouseDTO) {
        this.publishingHouseDTO = publishingHouseDTO;
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

    public Set<AuthorDTO> getAuthorDTOS() {
        return authorDTOS;
    }

    public void setAuthorDTOS(Set<AuthorDTO> authorDTOS) {
        this.authorDTOS = authorDTOS;
    }

    public void addAuthor(AuthorDTO authorDTO) {
        this.authorDTOS.add(authorDTO);
        authorDTO.getBookDTOS().add(this);
    }

    public void removeAuthor(AuthorDTO authorDTO) {
        this.authorDTOS.remove(authorDTO);
        authorDTO.getBookDTOS().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) &&
                Objects.equals(name, bookDTO.name) &&
                Objects.equals(genreBookDTOS, bookDTO.genreBookDTOS) &&
                Objects.equals(publishingHouseDTO, bookDTO.publishingHouseDTO) &&
                Objects.equals(publicationDate, bookDTO.publicationDate) &&
                Objects.equals(pathFile, bookDTO.pathFile) &&
                Objects.equals(commentBookDTOS, bookDTO.commentBookDTOS) &&
                // TODO: Check it, a cyclic call will be triggered here.
                Objects.equals(authorDTOS, bookDTO.authorDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genreBookDTOS, publishingHouseDTO, publicationDate, pathFile, commentBookDTOS,
                // TODO: Check it, a cyclic call will be triggered here.
                authorDTOS);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreBookDTOS=" + genreBookDTOS +
                ", publishingHouse=" + publishingHouseDTO +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", commentBookDTOS=" + commentBookDTOS +
                // TODO: Check it, a cyclic call will be triggered here.
                ", authorDTOS=" + authorDTOS +
                '}';
    }
}
