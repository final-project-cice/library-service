package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * This class is designed to represent DTO object of genre of book.
 *
 * @author Tsyupryk Roman
 */
public class GenreBookDTO {

    private Long id;
    private String name;

    @JsonIgnore
    private BookDTO book;

    public GenreBookDTO() {
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

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreBookDTO that = (GenreBookDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GenreBookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
