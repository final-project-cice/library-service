package com.trl.libraryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is designed to represent Entity object of phone number of publishing hose.
 *
 * @author Tsyupryk Roman
 */
@Entity(name = "PhoneNumberPublishingHouseEntity")
@Table(name = "phone_number_publishing_house")
public class PhoneNumberPublishingHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouseEntity publishingHouse;

    public PhoneNumberPublishingHouseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseEntity publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberPublishingHouseEntity that = (PhoneNumberPublishingHouseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, countryCode, type);
    }

    @Override
    public String toString() {
        return "PhoneNumberPublishingHouseEntity{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

