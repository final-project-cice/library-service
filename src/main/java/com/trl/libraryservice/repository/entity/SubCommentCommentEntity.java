package com.trl.libraryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is designed to represent Entity object of sub comment of comment book.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "SubCommentCommentEntity")
@Table(name = "sub_comment_comment")
public class SubCommentCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private Long userId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentBookEntity comment;

    public SubCommentCommentEntity() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CommentBookEntity getComment() {
        return comment;
    }

    public void setComment(CommentBookEntity comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCommentCommentEntity that = (SubCommentCommentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, date);
    }

    @Override
    public String toString() {
        return "SubCommentEntity{" +
                "id=" + id +
                ", user=" + userId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
