package com.trl.libraryservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void add() throws Exception {
        final String addedBookResult = "{\"id\":2,\"name\":\"My first Book\",\"genres\":[{\"id\":3,\"name\":\"Genre Book Uno\"},{\"id\":4,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":2,\"name\":\"Publication House\",\"address\":{\"id\":2,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":3,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":4,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":3,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\"},{\"id\":4,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":2,\"user\":{\"id\":3,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":2,\"user\":{\"id\":4,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\"}]}],\"authors\":[{\"id\":2,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":3,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\"},{\"id\":4,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":3,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":4,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":3,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":4,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":3,\"name\":\"Genre Uno\"},{\"id\":4,\"name\":\"Genre Dos\"}]}]}";

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(addedBookResult)));
    }

    @Test
    public void add_ParameterNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_BookNameNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_GenreListNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_PublishingHouseNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_PublicationDateNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_AuthorsListNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8082/books")
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user9QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"user\":{\"id\":null,\"firstName\":\"User FirstName\",\"lastName\":\"User LastName\",\"email\":\"email_user911QZ@email.com\",\"birthday\":\"12.06.2019\"},\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void getById() {
    }
}