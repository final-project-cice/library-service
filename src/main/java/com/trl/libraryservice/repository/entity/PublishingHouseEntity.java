package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
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
    private List<PhoneNumberPublishingHouseEntity> phoneNumbers;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPublishingHouseEntity> emails;

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

    public List<EmailPublishingHouseEntity> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailPublishingHouseEntity> emails) {
        this.emails = emails;
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
