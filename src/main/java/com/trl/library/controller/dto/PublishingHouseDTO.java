package com.trl.library.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
public class PublishingHouseDTO {

    private Long id;
    private String name;
    private AddressDTO address;

}
