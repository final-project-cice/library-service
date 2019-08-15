package com.trl.library.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "country", updatable = false, nullable = false)
    private String country;

    @Column(name = "city", updatable = false, nullable = false)
    private String city;

    @Column(name = "street", updatable = false, nullable = false)
    private String street;

    @Column(name = "houseNumber", updatable = false, nullable = false)
    private String houseNumber;

    @Column(name = "postcode", updatable = false, nullable = false)
    private Integer postcode;

}
