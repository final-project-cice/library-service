package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentBookDTO extends ResourceSupport {

    private Long commentId;
    private Long userId;
    private String text;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    private List<SubCommentCommentDTO> subComments = new ArrayList<>();

    @JsonIgnore
    private BookDTO book;

    public CommentBookDTO() { }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public List<SubCommentCommentDTO> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<SubCommentCommentDTO> subComments) {
        this.subComments = subComments;
    }

    public void addSubComment(SubCommentCommentDTO subComment) {
        this.subComments.add(subComment);
        subComment.setComment(this);
    }

    public void addSubComments(List<SubCommentCommentDTO> subCommentList) {
        subCommentList.forEach(this::addSubComment);
    }

    public void removeSubComment(SubCommentCommentDTO subComment) {
        this.subComments.remove(subComment);
        subComment.setComment(null);
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
        return Objects.equals(commentId, that.commentId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date) &&
                Objects.equals(subComments, that.subComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId, text, date, subComments);
    }

    @Override
    public String toString() {
        return "CommentBookDTO{" +
                "id=" + commentId +
                ", user=" + userId +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", subCommentCommentDTOS=" + subComments +
                '}';
    }
}
