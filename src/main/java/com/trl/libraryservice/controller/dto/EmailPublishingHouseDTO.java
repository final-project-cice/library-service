package com.trl.libraryservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * This class is designed to represent DTO object of email of publishing house.
 *
 * @author Tsyupryk Roman
 */
public class EmailPublishingHouseDTO {

    private Long id;
    private String email;
    private String emailType;

    @JsonIgnore
    private PublishingHouseDTO publishingHouse;

    public EmailPublishingHouseDTO() {
    }

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

    public PublishingHouseDTO getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseDTO publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailPublishingHouseDTO that = (EmailPublishingHouseDTO) o;
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
        return "EmailPublishingHouseDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailType='" + emailType + '\'' +
                '}';
    }
}
