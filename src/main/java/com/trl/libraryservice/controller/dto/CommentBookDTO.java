package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CommentBookDTO {

    private Long id;
    private UserDTO user;
    private String text;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    private List<SubCommentCommentDTO> subComments;
    private BookDTO book;

    public CommentBookDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<SubCommentCommentDTO> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<SubCommentCommentDTO> subComments) {
        this.subComments = subComments;
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
        CommentBookDTO that = (CommentBookDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date) &&
                Objects.equals(subComments, that.subComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text, date, subComments);
    }

    @Override
    public String toString() {
        return "CommentBookDTO{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", subCommentCommentDTOS=" + subComments +
                '}';
    }
}
