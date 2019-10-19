package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "SubCommentEntity")
@Table(name = "sub_comment_comment")
public class SubCommentCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UserEntity user;

    @Column(name = "test", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentBookEntity commentBookEntity;

    public SubCommentCommentEntity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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

    public CommentBookEntity getCommentBookEntity() {
        return commentBookEntity;
    }

    public void setCommentBookEntity(CommentBookEntity commentBookEntity) {
        this.commentBookEntity = commentBookEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCommentCommentEntity that = (SubCommentCommentEntity) o;
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
        return "SubCommentEntity{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
