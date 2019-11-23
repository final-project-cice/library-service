package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.Objects;

public class SubCommentCommentDTO extends ResourceSupport {

    private Long subCommentId;
    private Long userId;
    private String text;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonIgnore
    private CommentBookDTO comment;

    public SubCommentCommentDTO() { }

    public Long getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(Long subCommentId) {
        this.subCommentId = subCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.equals(subCommentId, that.subCommentId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCommentId, userId, text, date);
    }

    @Override
    public String toString() {
        return "SubCommentCommentDTO{" +
                "id=" + subCommentId +
                ", user=" + userId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
