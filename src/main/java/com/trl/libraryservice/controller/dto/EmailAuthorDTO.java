package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * This class is designed to represent DTO object of email of author.
 *
 * @author Tsyupryk Roman
 */
public class EmailAuthorDTO {

    private Long id;
    private String email;
    private String emailType;

    @JsonIgnore
    private AuthorDTO author;

    public EmailAuthorDTO() { }

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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAuthorDTO that = (EmailAuthorDTO) o;
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
        return "EmailAuthorDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailType='" + emailType + '\'' +
                '}';
    }
}
