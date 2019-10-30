package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "PublishingHouseEntity")
@Table(name = "publishing_house")
public class PublishingHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressPublishingHouseEntity address;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberPublishingHouseEntity> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPublishingHouseEntity> emails = new ArrayList<>();

    public PublishingHouseEntity() {
    }

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

    public AddressPublishingHouseEntity getAddress() {
        return address;
    }

    public void setAddress(AddressPublishingHouseEntity address) {
        this.address = address;
    }

    public List<PhoneNumberPublishingHouseEntity> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumberPublishingHouseEntity> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumberPublishingHouseEntity phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        phoneNumber.setPublishingHouse(this);
    }

    public void addPhoneNumbers(List<PhoneNumberPublishingHouseEntity> phoneNumberList) {
        phoneNumberList.forEach(this::addPhoneNumber);
    }

    public void removePhoneNumber(PhoneNumberPublishingHouseEntity phoneNumber) {
        this.phoneNumbers.remove(phoneNumber);
        phoneNumber.setPublishingHouse(null);
    }

    public List<EmailPublishingHouseEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailPublishingHouseEntity> emails) {
        this.emails = emails;
    }

    public void addEmail(EmailPublishingHouseEntity email) {
        this.emails.add(email);
        email.setPublishingHouse(this);
    }

    public void addEmails(List<EmailPublishingHouseEntity> emailList) {
        emailList.forEach(this::addEmail);
    }

    public void removeEmail(EmailPublishingHouseEntity email) {
        this.emails.remove(email);
        email.setPublishingHouse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseEntity that = (PublishingHouseEntity) o;
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
        return "PublishingHouseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressEntities=" + address +
                ", phoneNumberEntities=" + phoneNumbers +
                ", emailEntities=" + emails +
                '}';
    }
}
