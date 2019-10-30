package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class GenreAuthorDTO {

    private Long id;
    private String name;

    @JsonIgnore
    private AuthorDTO author;

    public GenreAuthorDTO() { }

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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreAuthorDTO that = (GenreAuthorDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GenreAuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
