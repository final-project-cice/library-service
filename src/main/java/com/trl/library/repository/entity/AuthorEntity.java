package com.trl.library.repository.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", updatable = false, nullable = false, unique = true)
    private String email;

    private Set<AddressEntity> address;
    // TODO: Mapping this member

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    private List<GenreEntity> genres;
    // TODO: Mapping this member
}
