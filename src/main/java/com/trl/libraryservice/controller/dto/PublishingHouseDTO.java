package com.trl.libraryservice.controller.dto;

import java.util.List;
import java.util.Objects;

public class PublishingHouseDTO {

    private Long id;
    private String name;
    private List<AddressPublishingHouseDTO> addressPublishingHouseDTOS;
    private List<PhoneNumberPublishingHouseDTO> phoneNumberPublishingHouseDTOS;
    private List<EmailPublishingHouseDTO> emailPublishingHouseDTOS;

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

    public List<AddressPublishingHouseDTO> getAddressPublishingHouseDTOS() {
        return addressPublishingHouseDTOS;
    }

    public void setAddressPublishingHouseDTOS(List<AddressPublishingHouseDTO> addressPublishingHouseDTOS) {
        this.addressPublishingHouseDTOS = addressPublishingHouseDTOS;
    }

    public List<PhoneNumberPublishingHouseDTO> getPhoneNumberPublishingHouseDTOS() {
        return phoneNumberPublishingHouseDTOS;
    }

    public void setPhoneNumberPublishingHouseDTOS(List<PhoneNumberPublishingHouseDTO> phoneNumberPublishingHouseDTOS) {
        this.phoneNumberPublishingHouseDTOS = phoneNumberPublishingHouseDTOS;
    }

    public List<EmailPublishingHouseDTO> getEmailPublishingHouseDTOS() {
        return emailPublishingHouseDTOS;
    }

    public void setEmailPublishingHouseDTOS(List<EmailPublishingHouseDTO> emailPublishingHouseDTOS) {
        this.emailPublishingHouseDTOS = emailPublishingHouseDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseDTO that = (PublishingHouseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(addressPublishingHouseDTOS, that.addressPublishingHouseDTOS) &&
                Objects.equals(phoneNumberPublishingHouseDTOS, that.phoneNumberPublishingHouseDTOS) &&
                Objects.equals(emailPublishingHouseDTOS, that.emailPublishingHouseDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addressPublishingHouseDTOS, phoneNumberPublishingHouseDTOS, emailPublishingHouseDTOS);
    }

    @Override
    public String toString() {
        return "PublishingHouseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressPublishingHouseDTOS=" + addressPublishingHouseDTOS +
                ", phoneNumberPublishingHouseDTOS=" + phoneNumberPublishingHouseDTOS +
                ", emailPublishingHouseDTOS=" + emailPublishingHouseDTOS +
                '}';
    }
}
