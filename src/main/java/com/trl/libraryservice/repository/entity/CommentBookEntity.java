package com.trl.libraryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is designed to represent Entity object of comment of book.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "CommentBookEntity")
@Table(name = "comment_book")
public class CommentBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDate date;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubCommentCommentEntity> subComments = new ArrayList<>();

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    public CommentBookEntity() {
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

    public List<SubCommentCommentEntity> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<SubCommentCommentEntity> subComments) {
        this.subComments = subComments;
    }

    public void addSubComment(SubCommentCommentEntity subComment) {
        this.subComments.add(subComment);
        subComment.setComment(this);
    }

    public void addSubComments(List<SubCommentCommentEntity> subCommentList) {
        subCommentList.forEach(this::addSubComment);
    }

    public void removeSubComment(SubCommentCommentEntity subComment) {
        this.subComments.remove(subComment);
        subComment.setComment(null);
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentBookEntity that = (CommentBookEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date) &&
                Objects.equals(subComments, that.subComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, date, subComments);
    }

    @Override
    public String toString() {
        return "CommentBookEntity{" +
                "id=" + id +
                ", user=" + userId +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", subComments=" + subComments +
                '}';
    }
}