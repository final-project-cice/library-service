package com.trl.libraryservice.temporary;

import com.trl.libraryservice.repository.AuthorRepository;
import com.trl.libraryservice.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    @Autowired
    public DatabaseLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /*Set<EmailEntity> emailEntitySet = Set.of(
            new EmailEntity(1L, "email_1"),
            new EmailEntity(2L, "email_2")
    );

    Set<PhoneNumberEntity> phoneNumberEntitySet = Set.of(
            new PhoneNumberEntity(1L, 1111111, "+11", phoneNumberType),
            new PhoneNumberEntity(2L, 2222222, "+2", phoneNumberType)
    );

    Set<AddressEntity> addressEntitySet = Set.of(
            new AddressEntity(1L, "Spain", "Madrid", "Calle_1", "1A", 11111),
            new AddressEntity(2L, "Spain", "Madrid", "Calle_2", "2A", 22222)
    );

    Set<GenreEntity> genreEntitySet = Set.of(
//            new GenreEntity(1L, "Terror"),
            new GenreEntity(2L, "Drama")
    );

    Set<BookEntity> bookEntitySet = Set.of(
      new BookEntity(1L, "Book_1", new GenreEntity(3L, "Default Gender"),
              new PublishingHouseEntity(1L, "PublicationHouse", new AddressEntity(3L, "Spain", "Madrid", "Calle_3", "3A", 3333)),
              LocalDate.of(2019, 1, 1), "path")
    );*/

    @Override
    public void run(String... strings) throws Exception {
//        authorRepository.save(new AuthorEntity(1L, "fn", "ln", emailEntitySet, phoneNumberEntitySet, addressEntitySet,
//                LocalDate.of(2019, 1,1), genreEntitySet, bookEntitySet));

//        authorRepository.save(new AuthorEntity(1L, "fn", "ln", emailEntitySet, phoneNumberEntitySet, addressEntitySet,
//                LocalDate.of(2019, 1,1), genreEntitySet));

//        authorRepository.save(new AuthorEntity(1L, "fn", "ln", LocalDate.of(2019, 1,1)));
    }
}

