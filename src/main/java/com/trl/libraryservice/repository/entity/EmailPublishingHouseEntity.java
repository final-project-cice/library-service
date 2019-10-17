package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "EmailPublishingHouseEntity")
@Table(name = "email_publishing_house")
public class EmailPublishingHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, table = "")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "email_type")
    private String emailType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouseEntity publishingHouseEntity;

    public EmailPublishingHouseEntity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public PublishingHouseEntity getPublishingHouseEntity() {
        return publishingHouseEntity;
    }

    public void setPublishingHouseEntity(PublishingHouseEntity publishingHouseEntity) {
        this.publishingHouseEntity = publishingHouseEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailPublishingHouseEntity that = (EmailPublishingHouseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(emailType, that.emailType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, emailType);
    }

    @Override
    public String toString() {
        return "EmailPublishingHouseEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailType='" + emailType + '\'' +
                '}';
    }
}
