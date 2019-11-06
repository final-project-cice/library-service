package com.trl.libraryservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/BookResource_add_Before.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(value = {"/BookResource_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {
        final String responseBodyContent = "{\"id\":1,\"name\":\"My first Book\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\"}]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}]}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void add_Parameter_Null() throws Exception {
        // TODO: Buscar la forma com se puede pasa parametro null.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "      ";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_BookName_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getName() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_BookName_Empty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getName() == is empty' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_GenreList_Null() throws Exception {
        // TODO: Finnd information how to pass value null. And terminate this test.
    }

    @Test
    public void add_GenreList_Empty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getGenres() is empty' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_PublishingHouse_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getPublishingHouse() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_PublicationDate_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getPublicationDate() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_AuthorSet_Null() throws Exception {
        // TODO: Finnd information how to pass value null. And terminate this test.
    }

    @Test
    public void add_AuthorSet_Empty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getAuthors() is empty' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/BookResource_getById_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BookResource_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {
        final String responseBodyContent = "{\"id\":1,\"name\":\"My first Book\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"30.10.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"02.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"30.10.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}]}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void getById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '0' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '-1' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/BookResource_getById_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getById_NotFoundBookById() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/BookResource_deleteById_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BookResource_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        this.mockMvc.perform(
                delete("http://localhost:8082/books/1"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                get("http://localhost:8082/books/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '0' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '-1' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/BookResource_deleteById_BookByIdNotExist_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BookResource_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById_BookByIdNotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }
}