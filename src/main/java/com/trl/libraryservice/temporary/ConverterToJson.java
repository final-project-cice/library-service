package com.trl.libraryservice.temporary;

import com.trl.libraryservice.controller.dto.AuthorDTO;
import com.trl.libraryservice.controller.dto.BookDTO;
import com.trl.libraryservice.repository.entity.*;
import com.trl.libraryservice.service.converter.AuthorConverter;
import com.trl.libraryservice.service.converter.BookConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConverterToJson {

    private static BookEntity bookEntity = new BookEntity();
    private static Set<AuthorEntity> authorEntities = new HashSet<>();

    public static void main(String[] args) {

        // Initialization
        init();

        // Book Convert
        BookDTO bookDTO = null;
            bookDTO = BookConverter.mapEntityToDTO(bookEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonBookDTO = objectMapper.writeValueAsString(bookDTO);
            System.out.println(jsonBookDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Author Convert
        Set<AuthorDTO> authorDTOS = null;
            authorDTOS = AuthorConverter.mapSetEntityToSetDTO(authorEntities);

        try {
            System.out.println();
            String jsonAuthorDTO = objectMapper.writeValueAsString(authorDTOS);
            System.out.println(jsonAuthorDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        // ================================= The creation genre Book
        List<GenreBookEntity> genreBookEntities = new ArrayList<>();

        GenreBookEntity genreBookEntity_1 = new GenreBookEntity();
        genreBookEntity_1.setName("Genre Book Uno");
        genreBookEntity_1.setBook(bookEntity);

        GenreBookEntity genreBookEntity_2 = new GenreBookEntity();
        genreBookEntity_2.setName("Genre Book Dos");
        genreBookEntity_2.setBook(bookEntity);

        genreBookEntities.add(genreBookEntity_1);
        genreBookEntities.add(genreBookEntity_2);

        // ================================= The creation publicationHouse Book
        PublishingHouseEntity publishingHouseEntity = new PublishingHouseEntity();

        // The creation AddressPublicationHouseEntity for PublicationHouseEntity
        AddressPublishingHouseEntity addressPublishingHouseEntity = new AddressPublishingHouseEntity();
        addressPublishingHouseEntity.setCountry("Spain");
        addressPublishingHouseEntity.setCity("Madrid");
        addressPublishingHouseEntity.setStreet("Calle Uno");
        addressPublishingHouseEntity.setHouseNumber("1A");
        addressPublishingHouseEntity.setPostcode(1111);

        // The creation PhoneNumberPublicationHouseEntity for PublicationHouseEntity
        List<PhoneNumberPublishingHouseEntity> phoneNumberPublishingHouseEntities = new ArrayList<>();

        PhoneNumberPublishingHouseEntity phoneNumberPublishingHouseEntity_1 = new PhoneNumberPublishingHouseEntity();
        phoneNumberPublishingHouseEntity_1.setPhoneNumber("0111111111111111");
        phoneNumberPublishingHouseEntity_1.setCountryCode("11111");
        phoneNumberPublishingHouseEntity_1.setType("Office");
        phoneNumberPublishingHouseEntity_1.setPublishingHouse(publishingHouseEntity);

        PhoneNumberPublishingHouseEntity phoneNumberPublishingHouseEntity_2 = new PhoneNumberPublishingHouseEntity();
        phoneNumberPublishingHouseEntity_2.setPhoneNumber("02222222222222");
        phoneNumberPublishingHouseEntity_2.setCountryCode("222222");
        phoneNumberPublishingHouseEntity_2.setType("Fax");
        phoneNumberPublishingHouseEntity_2.setPublishingHouse(publishingHouseEntity);

        phoneNumberPublishingHouseEntities.add(phoneNumberPublishingHouseEntity_1);
        phoneNumberPublishingHouseEntities.add(phoneNumberPublishingHouseEntity_2);

        // The creation EmailPublicationHouseEntity for PublicationHouseEntity
        List<EmailPublishingHouseEntity> emailPublishingHouseEntities = new ArrayList<>();

        EmailPublishingHouseEntity emailPublishingHouseEntity_1 = new EmailPublishingHouseEntity();
        emailPublishingHouseEntity_1.setEmail("email_1_publicationHouse9QZ@email.com");
        emailPublishingHouseEntity_1.setEmailType("Office");
        emailPublishingHouseEntity_1.setPublishingHouse(publishingHouseEntity);

        EmailPublishingHouseEntity emailPublishingHouseEntity_2 = new EmailPublishingHouseEntity();
        emailPublishingHouseEntity_2.setEmail("email_2_publicationHouse9QZ@email.com");
        emailPublishingHouseEntity_2.setEmailType("Office 2");
        emailPublishingHouseEntity_2.setPublishingHouse(publishingHouseEntity);

        emailPublishingHouseEntities.add(emailPublishingHouseEntity_1);
        emailPublishingHouseEntities.add(emailPublishingHouseEntity_2);

        // ================================= Filling in publicationHouse fields.
        publishingHouseEntity.setName("Publication House");
        publishingHouseEntity.setAddress(addressPublishingHouseEntity);
        publishingHouseEntity.setPhoneNumbers(phoneNumberPublishingHouseEntities);
        publishingHouseEntity.setEmails(emailPublishingHouseEntities);


        // ================================= The creation CommentBook Book
        List<CommentBookEntity> commentBookEntities = new ArrayList<>();

        CommentBookEntity commentBookEntity_1 = new CommentBookEntity();

        // The creation SubComment for CommentBookEntity
        List<SubCommentCommentEntity> subCommentCommentEntities = new ArrayList<>();
        SubCommentCommentEntity subCommentCommentEntity_1 = new SubCommentCommentEntity();


        subCommentCommentEntity_1.setUserId(1L);
        subCommentCommentEntity_1.setText("test sub comment");
        subCommentCommentEntity_1.setDate(LocalDate.now());
        subCommentCommentEntity_1.setComment(commentBookEntity_1);

        subCommentCommentEntities.add(subCommentCommentEntity_1);


        // ================================= Filling in commentBook Book
        commentBookEntity_1.setUserId(1L);
        commentBookEntity_1.setText("Text Comment");
        commentBookEntity_1.setDate(LocalDate.now());
        commentBookEntity_1.setSubComments(subCommentCommentEntities);
        commentBookEntity_1.setBook(bookEntity);

        commentBookEntities.add(commentBookEntity_1);


        // ================================= The creation Author 1
        authorEntities = new HashSet<>();

        AuthorEntity author_1 = new AuthorEntity();

        // The creation emails for Author 1.
        List<EmailAuthorEntity> emailAuthor_1_Entities = new ArrayList<>();

        EmailAuthorEntity emailAuthorEntity_1 = new EmailAuthorEntity();
        emailAuthorEntity_1.setEmail("email_1.author_19QZ@email.com");
        emailAuthorEntity_1.setEmailType("Personal");
        emailAuthorEntity_1.setAuthor(author_1);

        EmailAuthorEntity emailAuthorEntity_2 = new EmailAuthorEntity();
        emailAuthorEntity_2.setEmail("email_2.author_19QZ@email.com");
        emailAuthorEntity_2.setEmailType("Personal");
        emailAuthorEntity_2.setAuthor(author_1);

        emailAuthor_1_Entities.add(emailAuthorEntity_1);
        emailAuthor_1_Entities.add(emailAuthorEntity_2);

        // The creation phoneNumbers for Author 1
        List<PhoneNumberAuthorEntity> phoneNumberAuthor_1_Entities = new ArrayList<>();

        PhoneNumberAuthorEntity phoneNumberAuthorEntity_1 = new PhoneNumberAuthorEntity();
        phoneNumberAuthorEntity_1.setPhoneNumber("01111111111111");
        phoneNumberAuthorEntity_1.setCountryCode("111");
        phoneNumberAuthorEntity_1.setType("Personal");
        phoneNumberAuthorEntity_1.setAuthor(author_1);

        PhoneNumberAuthorEntity phoneNumberAuthorEntity_2 = new PhoneNumberAuthorEntity();
        phoneNumberAuthorEntity_2.setPhoneNumber("0222222222222222");
        phoneNumberAuthorEntity_2.setCountryCode("222");
        phoneNumberAuthorEntity_2.setType("Work");
        phoneNumberAuthorEntity_2.setAuthor(author_1);

        phoneNumberAuthor_1_Entities.add(phoneNumberAuthorEntity_1);
        phoneNumberAuthor_1_Entities.add(phoneNumberAuthorEntity_2);

        // The creation address for Author 1
        List<AddressAuthorEntity> addressAuthor_1_Entities = new ArrayList<>();

        AddressAuthorEntity addressAuthorEntity_1 = new AddressAuthorEntity();
        addressAuthorEntity_1.setCountry("Spain");
        addressAuthorEntity_1.setCity("Madrid");
        addressAuthorEntity_1.setStreet("Calle Uno");
        addressAuthorEntity_1.setHouseNumber("1A");
        addressAuthorEntity_1.setPostcode(111111);
        addressAuthorEntity_1.setAuthor(author_1);

        AddressAuthorEntity addressAuthorEntity_2 = new AddressAuthorEntity();
        addressAuthorEntity_2.setCountry("Spain");
        addressAuthorEntity_2.setCity("Barcelona");
        addressAuthorEntity_2.setStreet("Calle Dos");
        addressAuthorEntity_2.setHouseNumber("2A");
        addressAuthorEntity_2.setPostcode(222222);
        addressAuthorEntity_2.setAuthor(author_1);

        addressAuthor_1_Entities.add(addressAuthorEntity_1);
        addressAuthor_1_Entities.add(addressAuthorEntity_2);

        // The creation genre for Author 1
        List<GenreAuthorEntity> genreAuthor_1_Entities = new ArrayList<>();

        GenreAuthorEntity genreAuthorEntity_1 = new GenreAuthorEntity();
        genreAuthorEntity_1.setName("Genre Uno");
        genreAuthorEntity_1.setAuthor(author_1);

        GenreAuthorEntity genreAuthorEntity_2 = new GenreAuthorEntity();
        genreAuthorEntity_2.setName("Genre Dos");
        genreAuthorEntity_2.setAuthor(author_1);

        genreAuthor_1_Entities.add(genreAuthorEntity_1);
        genreAuthor_1_Entities.add(genreAuthorEntity_2);

        // The creation book for Author 1
        Set<BookEntity> bookEntitiesAuthor_1 = new HashSet<>();
        bookEntitiesAuthor_1.add(bookEntity);

        // ================================= Filling in author fields.
        author_1.setFirstName("Author_1 FirstName");
        author_1.setLastName("Author_1 LastName");
        author_1.setEmails(emailAuthor_1_Entities);
        author_1.setPhoneNumbers(phoneNumberAuthor_1_Entities);
        author_1.setAddresses(addressAuthor_1_Entities);
        author_1.setBirthday(LocalDate.now());
        author_1.setGenres(genreAuthor_1_Entities);
        author_1.setBooks(bookEntitiesAuthor_1);

        authorEntities.add(author_1);

        // ==================================================================== Filling in book fields.
        bookEntity.setName("My first Book");
        bookEntity.setGenres(genreBookEntities);
        bookEntity.setPublishingHouse(publishingHouseEntity);
        bookEntity.setPublicationDate(LocalDate.now());
        bookEntity.setPathFile("path/path/book");
        bookEntity.setComments(commentBookEntities);
        bookEntity.setAuthors(authorEntities);
    }
}
/*

"12.06.2019"

*/