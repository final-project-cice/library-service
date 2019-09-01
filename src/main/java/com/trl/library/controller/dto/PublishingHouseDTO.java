package com.trl.library.controller.dto;

import java.util.Objects;

public class PublishingHouseDTO {

    private Long id;
    private String name;
    private AddressDTO address;

    public PublishingHouseDTO() {
    }

    public PublishingHouseDTO(Long id, String name, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseDTO that = (PublishingHouseDTO) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "PublishingHouseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

}
