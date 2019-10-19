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

    private List<SubCommentCommentDTO> subCommentCommentDTOS;
    private BookDTO bookDTO;

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

    public List<SubCommentCommentDTO> getSubCommentCommentDTOS() {
        return subCommentCommentDTOS;
    }

    public void setSubCommentCommentDTOS(List<SubCommentCommentDTO> subCommentCommentDTOS) {
        this.subCommentCommentDTOS = subCommentCommentDTOS;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
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
                Objects.equals(subCommentCommentDTOS, that.subCommentCommentDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text, date, subCommentCommentDTOS);
    }

    @Override
    public String toString() {
        return "CommentBookDTO{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", subCommentCommentDTOS=" + subCommentCommentDTOS +
                '}';
    }
}
