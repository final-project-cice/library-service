package com.trl.libraryservice.controller.dto;

import java.util.Objects;

public class CommentDTO {

    private Long id;
    private Long userId;
    private String text;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Long userId, String text) {
        this.id = id;
        this.userId = userId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return id.equals(that.id) &&
                userId.equals(that.userId) &&
                text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                '}';
    }

}
