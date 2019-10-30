package com.trl.libraryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "AddressAuthorEntity")
@Table(name = "address_author")
public class AddressAuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "postcode", nullable = false)
    private Integer postcode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    public AddressAuthorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressAuthorEntity that = (AddressAuthorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(postcode, that.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, houseNumber, postcode);
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
