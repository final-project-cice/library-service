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

    @OneToMany(mappedBy = "publishingHouseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressPublishingHouseEntity> addressPublishingHouseEntities;

    @OneToMany(mappedBy = "publishingHouseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumberPublishingHouseEntity> phoneNumberPublishingHouseEntities;

    @OneToMany(mappedBy = "publishingHouseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailPublishingHouseEntity> emailPublishingHouseEntities;

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

    public List<AddressPublishingHouseEntity> getAddressPublishingHouseEntities() {
        return addressPublishingHouseEntities;
    }

    public void setAddressPublishingHouseEntities(List<AddressPublishingHouseEntity> addressPublishingHouseEntities) {
        this.addressPublishingHouseEntities = addressPublishingHouseEntities;
    }

    public List<PhoneNumberPublishingHouseEntity> getPhoneNumberPublishingHouseEntities() {
        return phoneNumberPublishingHouseEntities;
    }

    public void setPhoneNumberPublishingHouseEntities(List<PhoneNumberPublishingHouseEntity> phoneNumberPublishingHouseEntities) {
        this.phoneNumberPublishingHouseEntities = phoneNumberPublishingHouseEntities;
    }

    public List<EmailPublishingHouseEntity> getEmailPublishingHouseEntities() {
        return emailPublishingHouseEntities;
    }

    public void setEmailPublishingHouseEntities(List<EmailPublishingHouseEntity> emailPublishingHouseEntities) {
        this.emailPublishingHouseEntities = emailPublishingHouseEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseEntity that = (PublishingHouseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(addressPublishingHouseEntities, that.addressPublishingHouseEntities) &&
                Objects.equals(phoneNumberPublishingHouseEntities, that.phoneNumberPublishingHouseEntities) &&
                Objects.equals(emailPublishingHouseEntities, that.emailPublishingHouseEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addressPublishingHouseEntities, phoneNumberPublishingHouseEntities, emailPublishingHouseEntities);
    }

    @Override
    public String toString() {
        return "PublishingHouseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressEntities=" + addressPublishingHouseEntities +
                ", phoneNumberEntities=" + phoneNumberPublishingHouseEntities +
                ", emailEntities=" + emailPublishingHouseEntities +
                '}';
    }

}
