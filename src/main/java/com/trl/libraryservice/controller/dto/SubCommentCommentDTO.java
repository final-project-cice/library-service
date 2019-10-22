package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class SubCommentCommentDTO {

    private Long id;
    private UserDTO user;
    private String text;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    private CommentBookDTO comment;

    public SubCommentCommentDTO() { }

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

    public CommentBookDTO getComment() {
        return comment;
    }

    public void setComment(CommentBookDTO comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCommentCommentDTO that = (SubCommentCommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text, date);
    }

    @Override
    public String toString() {
        return "SubCommentCommentDTO{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
