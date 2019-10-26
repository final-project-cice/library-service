package com.trl.libraryservice.controller.dto;

import java.util.List;
import java.util.Objects;

public class PublishingHouseDTO {

    private Long id;
    private String name;
    private AddressPublishingHouseDTO address;
    private List<PhoneNumberPublishingHouseDTO> phoneNumbers;
    private List<EmailPublishingHouseDTO> emails;

    public PublishingHouseDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressPublishingHouseDTO getAddress() {
        return address;
    }

    public void setAddress(AddressPublishingHouseDTO address) {
        this.address = address;
    }

    public List<PhoneNumberPublishingHouseDTO> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberPublishingHouseDTO> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<EmailPublishingHouseDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailPublishingHouseDTO> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseDTO that = (PublishingHouseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNumbers, that.phoneNumbers) &&
                Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phoneNumbers, emails);
    }

    @Override
    public String toString() {
        return "PublishingHouseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressPublishingHouseDTOS=" + address +
                ", phoneNumberPublishingHouseDTOS=" + phoneNumbers +
                ", emailPublishingHouseDTOS=" + emails +
                '}';
    }
}
