package com.trl.libraryservice.controller.dto;

import java.util.Objects;

public class PhoneNumberPublishingHouseDTO {

    private Long id;
    private String phoneNumber;
    private String countryCode;
    private String type;
    private PublishingHouseDTO publishingHouse;

    public PhoneNumberPublishingHouseDTO() { }

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
        PhoneNumberPublishingHouseDTO that = (PhoneNumberPublishingHouseDTO) o;
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
        return "PhoneNumberPublishingHouseDTO{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

