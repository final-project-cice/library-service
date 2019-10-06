package com.trl.libraryservice.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone_number")
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "country_code")
    private String countryCode;

    public PhoneNumberEntity() {
    }

    public PhoneNumberEntity(Long id, Integer phoneNumber, String countryCode) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberEntity that = (PhoneNumberEntity) o;
        return id.equals(that.id) &&
                phoneNumber.equals(that.phoneNumber) &&
                countryCode.equals(that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, countryCode);
    }

    @Override
    public String toString() {
        return "PhoneNumberEntity{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

}
