package com.trl.library.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<AddressDTO> address;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private List<GenreDto> genres;

}
