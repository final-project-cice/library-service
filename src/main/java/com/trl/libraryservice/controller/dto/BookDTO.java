package com.trl.libraryservice.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookDTO {

    private Long id;
    private String name;
    private Set<AuthorDTO> authors;
    private GenreDTO genre;
    private PublishingHouseDTO publishingHouse;
    private LocalDate publicationDate;
    private String pathFile;
    private Integer rating;
    private List<CommentDTO> comments;

    public BookDTO() {
    }

    public BookDTO(Long id, String name, Set<AuthorDTO> authors, GenreDTO genre, PublishingHouseDTO publishingHouse, LocalDate publicationDate, String pathFile, Integer rating, List<CommentDTO> comments) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.publicationDate = publicationDate;
        this.pathFile = pathFile;
        this.rating = rating;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public PublishingHouseDTO getPublishingHouse() {
        return publishingHouse;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPathFile() {
        return pathFile;
    }

    public Integer getRating() {
        return rating;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id.equals(bookDTO.id) &&
                name.equals(bookDTO.name) &&
                authors.equals(bookDTO.authors) &&
                genre.equals(bookDTO.genre) &&
                publishingHouse.equals(bookDTO.publishingHouse) &&
                publicationDate.equals(bookDTO.publicationDate) &&
                pathFile.equals(bookDTO.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genre, publishingHouse, publicationDate, pathFile);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genre=" + genre +
                ", publishingHouse=" + publishingHouse +
                ", publicationDate=" + publicationDate +
                ", pathFile='" + pathFile + '\'' +
                ", rating=" + rating +
                ", comments=" + comments +
                '}';
    }

}
