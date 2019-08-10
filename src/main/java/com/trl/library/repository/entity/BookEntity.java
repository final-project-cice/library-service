package com.trl.library.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    private List<AuthorEntity> authors;
    // TODO: Mapping this member

    @Column(name = "genre", updatable = false, nullable = false)
    private GenreEntity genre;

    @Column(name = "publishingHouse", updatable = false, nullable = false)
    private PublishingHouseEntity publishingHouse;

    @Column(name = "publicationDate", updatable = false, nullable = false)
    private LocalDate publicationDate;

    @Column(name = "content", nullable = false)
    private String content;

}
