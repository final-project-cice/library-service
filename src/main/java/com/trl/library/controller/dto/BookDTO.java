package com.trl.library.controller.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
public class BookDTO {

    private Long id;
    private String name;
    private Set<AuthorDTO> authors;
    private GenreDTO genre;
    private PublishingHouseDTO publishingHouse;
    private LocalDate publicationDate;
    private String pathFile;

}
