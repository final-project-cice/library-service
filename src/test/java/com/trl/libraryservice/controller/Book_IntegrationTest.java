package com.trl.libraryservice.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class Book_IntegrationTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-docs");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private static final String BASE_URL = "http://localhost:8082";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults()
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Sql(value = {"/Book_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {

        final String responseBodyContent = "{\"id\":1,\"name\":\"My first Book\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\"}]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}]}";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("books/add",
                        requestFields(
                                fieldWithPath("id").description("The id of Book."),
                                fieldWithPath("name").description("The name of Book. This field is required."),
                                fieldWithPath("genres.[]").description("The array of Genres of the Book. This array of genres is required."),
                                fieldWithPath("genres.[].id").description("The id of Genre."),
                                fieldWithPath("genres.[].name").description("The name of the genre of the Book. This field is required."),
                                fieldWithPath("genres.[].book").ignored(),
                                fieldWithPath("publishingHouse").description("The Publishing House of the Book. This field is required."),
                                fieldWithPath("publishingHouse.id").description("The id of Publishing House."),
                                fieldWithPath("publishingHouse.name").description("The name of the Publishing House of the Book. This field is required."),
                                fieldWithPath("publishingHouse.address").description("The Address of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.address.id").description("The id of Address."),
                                fieldWithPath("publishingHouse.address.country").description("The name of the country where located Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.address.city").description("The name of the city where located Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.address.street").description("The name of the street where located Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.address.houseNumber").description("The house number where located Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.address.postcode").description("The postcode where located Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[]").description("The array of phone numbers of the Publishing House. This array of phone numbers is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].id").description("The id of the phone number of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].phoneNumber").description("The phone number of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].countryCode").description("The country code of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].type").description("The type of the phone number of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].publishingHouse").ignored(),
                                fieldWithPath("publishingHouse.emails.[]").description("The array of email of the Publishing House. This array of emails is required."),
                                fieldWithPath("publishingHouse.emails.[].id").description("The id of the email of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].email").description("The email of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.emails.[].emailType").description("The email type of the Publishing House. This field is required."),
                                fieldWithPath("publishingHouse.emails.[].publishingHouse").ignored(),
                                fieldWithPath("publicationDate").description("The publicarion date of the Book. This field is required."),
                                fieldWithPath("pathFile").description("The path file of the Book. This field is required."),
                                fieldWithPath("comments.[]").description("The array of comment of the Book. This array of comments is oprional."),
                                fieldWithPath("comments.[].id").description("The id of the comment of the Book."),
                                fieldWithPath("comments.[].userId").description("The id of use of the comment of the Book."),
                                fieldWithPath("comments.[].text").description("The text of the comment of the Book."),
                                fieldWithPath("comments.[].date").description("The date of the comment of the Book."),
                                fieldWithPath("comments.[].book").ignored(),
                                fieldWithPath("comments.[].subComments.[]").description("The array of subComment of the Comment. This array of subComments is oprional."),
                                fieldWithPath("comments.[].subComments.[].id").description("The id of the subComment of the Comment."),
                                fieldWithPath("comments.[].subComments.[].userId").description("The id of user of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].text").description("The text of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].date").description("The date of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].comment").ignored(),
                                fieldWithPath("authors.[]").description("The array of the Author of the Book. This array of authors is required."),
                                fieldWithPath("authors.[].id").description("The id of the Author of the Book."),
                                fieldWithPath("authors.[].firstName").description("The first name  of the Author. This field is required."),
                                fieldWithPath("authors.[].lastName").description("The last name  of the Author. This field is required."),
                                fieldWithPath("authors.[].emails.[]").description("The array of emails of the Author. This array of emails is required."),
                                fieldWithPath("authors.[].emails.[].id").description("The id of email of the Author."),
                                fieldWithPath("authors.[].emails.[].email").description("The email of the Author. This field is required."),
                                fieldWithPath("authors.[].emails.[].emailType").description("The email type of the email. This field is required."),
                                fieldWithPath("authors.[].emails.[].author").ignored(),
                                fieldWithPath("authors.[].phoneNumbers.[]").description("The array of phone number of the Author. This array of phone numbers is required."),
                                fieldWithPath("authors.[].phoneNumbers.[].id").description("The id of the phone number of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].phoneNumber").description("The phone number of the Author. This field is required."),
                                fieldWithPath("authors.[].phoneNumbers.[].countryCode").description("The country code of the phone number. This field is required."),
                                fieldWithPath("authors.[].phoneNumbers.[].type").description("The type of the phone number. This field is required."),
                                fieldWithPath("authors.[].phoneNumbers.[].author").ignored(),
                                fieldWithPath("authors.[].addresses.[]").description("The array of addresses of the Author. This array of addresses is required."),
                                fieldWithPath("authors.[].addresses.[].id").description("The id of address of the Author."),
                                fieldWithPath("authors.[].addresses.[].country").description("The country of the Author. This field is required."),
                                fieldWithPath("authors.[].addresses.[].city").description("The city of the Author. This field is required."),
                                fieldWithPath("authors.[].addresses.[].street").description("The street of the Author. This field is required."),
                                fieldWithPath("authors.[].addresses.[].houseNumber").description("The house number of the Author. This field is required."),
                                fieldWithPath("authors.[].addresses.[].postcode").description("The postcode of the Author. This field is required."),
                                fieldWithPath("authors.[].addresses.[].author").ignored(),
                                fieldWithPath("authors.[].birthday").description("The birthday of the Author. This field is required."),
                                fieldWithPath("authors.[].genres.[]").description("The array of genres of the Author. This array of genres is required."),
                                fieldWithPath("authors.[].genres.[].id").description("The id of the genre of the Author."),
                                fieldWithPath("authors.[].genres.[].name").description("The name of the genre of the Author. This field is required."),
                                fieldWithPath("authors.[].genres.[].author").ignored(),
                                fieldWithPath("authors.[].books").ignored()),
                        responseFields(
                                fieldWithPath("id").description("The id of Book."),
                                fieldWithPath("name").description("The name of Book."),
                                fieldWithPath("genres.[]").description("The array of Genres of the Book."),
                                fieldWithPath("genres.[].id").description("The id of Genre."),
                                fieldWithPath("genres.[].name").description("The name of the genre of the Book."),
                                fieldWithPath("publishingHouse").description("The Publishing House of the Book."),
                                fieldWithPath("publishingHouse.id").description("The id of Publishing House."),
                                fieldWithPath("publishingHouse.name").description("The name of the Publishing House of the Book."),
                                fieldWithPath("publishingHouse.address").description("The Address of the Publishing House."),
                                fieldWithPath("publishingHouse.address.id").description("The id of Address."),
                                fieldWithPath("publishingHouse.address.country").description("The name of the country where located Publishing House."),
                                fieldWithPath("publishingHouse.address.city").description("The name of the city where located Publishing House."),
                                fieldWithPath("publishingHouse.address.street").description("The name of the street where located Publishing House."),
                                fieldWithPath("publishingHouse.address.houseNumber").description("The house number where located Publishing House."),
                                fieldWithPath("publishingHouse.address.postcode").description("The postcode where located Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[]").description("The array of phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].id").description("The id of the phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].phoneNumber").description("The phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].countryCode").description("The country code of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].type").description("The type of the phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[]").description("The array of emails of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].id").description("The id of the email of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].email").description("The email of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].emailType").description("The email type of the Publishing House."),
                                fieldWithPath("publicationDate").description("The publicarion date of the Book."),
                                fieldWithPath("pathFile").description("The path file of the Book."),
                                fieldWithPath("comments.[]").description("The array of comments of the Book."),
                                fieldWithPath("comments.[].id").description("The id of the comment of the Book."),
                                fieldWithPath("comments.[].userId").description("The id of use of the comment of the Book."),
                                fieldWithPath("comments.[].text").description("The text of the comment of the Book."),
                                fieldWithPath("comments.[].date").description("The date of the comment of the Book."),
                                fieldWithPath("comments.[].subComments.[]").description("The array of subComment of the Comment."),
                                fieldWithPath("comments.[].subComments.[].id").description("The id of the subComment of the Comment."),
                                fieldWithPath("comments.[].subComments.[].userId").description("The id of user of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].text").description("The text of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].date").description("The date of the SubComment."),
                                fieldWithPath("authors.[]").description("The array of Authors of the Book."),
                                fieldWithPath("authors.[].id").description("The id of the Author of the Book."),
                                fieldWithPath("authors.[].firstName").description("The first name  of the Author."),
                                fieldWithPath("authors.[].lastName").description("The last name  of the Author."),
                                fieldWithPath("authors.[].emails.[]").description("The array of emails of the Author."),
                                fieldWithPath("authors.[].emails.[].id").description("The id of email of the Author."),
                                fieldWithPath("authors.[].emails.[].email").description("The email of the Author."),
                                fieldWithPath("authors.[].emails.[].emailType").description("The email type of the email."),
                                fieldWithPath("authors.[].phoneNumbers.[]").description("The array of phone numbers of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].id").description("The id of the phone number of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].phoneNumber").description("The phone number of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].countryCode").description("The country code of the phone number."),
                                fieldWithPath("authors.[].phoneNumbers.[].type").description("The type of the phone number."),
                                fieldWithPath("authors.[].addresses.[]").description("The array of addresses of the Author."),
                                fieldWithPath("authors.[].addresses.[].id").description("The id of address of the Author."),
                                fieldWithPath("authors.[].addresses.[].country").description("The country of the Author."),
                                fieldWithPath("authors.[].addresses.[].city").description("The city of the Author."),
                                fieldWithPath("authors.[].addresses.[].street").description("The street of the Author."),
                                fieldWithPath("authors.[].addresses.[].houseNumber").description("The house number of the Author."),
                                fieldWithPath("authors.[].addresses.[].postcode").description("The postcode of the Author."),
                                fieldWithPath("authors.[].birthday").description("The birthday of the Author."),
                                fieldWithPath("authors.[].genres.[]").description("The array of genres of the Author."),
                                fieldWithPath("authors.[].genres.[].id").description("The id of the genre of the Author."),
                                fieldWithPath("authors.[].genres.[].name").description("The name of the genre of the Author.")))
                );
    }

    @Test
    public void add_Parameter_Null() throws Exception {
        // TODO: Buscar la forma com se puede pasa parametro null.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "      ";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_BookName_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getName() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_BookName_Empty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getName() == is empty' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
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
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"My first Book\",\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_PublishingHouse_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getPublishingHouse() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_PublicationDate_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'book.getPublicationDate() == null' is illegal, check the field that it has the 'book' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}],\"authors\":[{\"id\":null,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":null,\"email\":\"email_1.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null},{\"id\":null,\"email\":\"email_2.author_19QZ@email.com\",\"emailType\":\"Personal\",\"author\":null}],\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\",\"author\":null},{\"id\":null,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\",\"author\":null}],\"addresses\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111,\"author\":null},{\"id\":null,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222,\"author\":null}],\"birthday\":\"12.06.2019\",\"genres\":[{\"id\":null,\"name\":\"Genre Uno\",\"author\":null},{\"id\":null,\"name\":\"Genre Dos\",\"author\":null}],\"books\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
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
                post(BASE_URL + "/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"id\":null,\"name\":\"My first Book\",\"genres\":[{\"id\":null,\"name\":\"Genre Book Uno\",\"book\":null},{\"id\":null,\"name\":\"Genre Book Dos\",\"book\":null}],\"publishingHouse\":{\"id\":null,\"name\":\"Publication House\",\"address\":{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":null,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\",\"publishingHouse\":null}],\"emails\":[{\"id\":null,\"email\":\"email_1_publicationHouse9QZ@email.com\",\"emailType\":\"Office\",\"publishingHouse\":null},{\"id\":null,\"email\":\"email_2_publicationHouse9QZ@email.com\",\"emailType\":\"Office 2\",\"publishingHouse\":null}]},\"publicationDate\":\"12.06.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":null,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"12.06.2019\",\"subComments\":[{\"id\":null,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"12.06.2019\",\"comment\":null}],\"book\":null}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {

        final String responseBodyContent = "{\"id\":1,\"name\":\"BBBBBBBBB\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"30.10.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"02.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"30.10.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}]}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("books/get-by-id",
                        pathParameters(
                                parameterWithName("bookId").description("The bookId used to search for a book.")),
                        responseFields(
                                fieldWithPath("id").description("The id of Book."),
                                fieldWithPath("name").description("The name of Book."),
                                fieldWithPath("genres.[]").description("The array of Genres of the Book."),
                                fieldWithPath("genres.[].id").description("The id of Genre."),
                                fieldWithPath("genres.[].name").description("The name of the genre of the Book."),
                                fieldWithPath("publishingHouse").description("The Publishing House of the Book."),
                                fieldWithPath("publishingHouse.id").description("The id of Publishing House."),
                                fieldWithPath("publishingHouse.name").description("The name of the Publishing House of the Book."),
                                fieldWithPath("publishingHouse.address").description("The Address of the Publishing House."),
                                fieldWithPath("publishingHouse.address.id").description("The id of Address."),
                                fieldWithPath("publishingHouse.address.country").description("The name of the country where located Publishing House."),
                                fieldWithPath("publishingHouse.address.city").description("The name of the city where located Publishing House."),
                                fieldWithPath("publishingHouse.address.street").description("The name of the street where located Publishing House."),
                                fieldWithPath("publishingHouse.address.houseNumber").description("The house number where located Publishing House."),
                                fieldWithPath("publishingHouse.address.postcode").description("The postcode where located Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[]").description("The array of phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].id").description("The id of the phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].phoneNumber").description("The phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].countryCode").description("The country code of the Publishing House."),
                                fieldWithPath("publishingHouse.phoneNumbers.[].type").description("The type of the phone number of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[]").description("The array of emails of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].id").description("The id of the email of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].email").description("The email of the Publishing House."),
                                fieldWithPath("publishingHouse.emails.[].emailType").description("The email type of the Publishing House."),
                                fieldWithPath("publicationDate").description("The publicarion date of the Book."),
                                fieldWithPath("pathFile").description("The path file of the Book."),
                                fieldWithPath("comments.[]").description("The array of comments of the Book."),
                                fieldWithPath("comments.[].id").description("The id of the comment of the Book."),
                                fieldWithPath("comments.[].userId").description("The id of use of the comment of the Book."),
                                fieldWithPath("comments.[].text").description("The text of the comment of the Book."),
                                fieldWithPath("comments.[].date").description("The date of the comment of the Book."),
                                fieldWithPath("comments.[].subComments.[]").description("The array of subComment of the Comment."),
                                fieldWithPath("comments.[].subComments.[].id").description("The id of the subComment of the Comment."),
                                fieldWithPath("comments.[].subComments.[].userId").description("The id of user of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].text").description("The text of the SubComment."),
                                fieldWithPath("comments.[].subComments.[].date").description("The date of the SubComment."),
                                fieldWithPath("authors.[]").description("The array of Authors of the Book."),
                                fieldWithPath("authors.[].id").description("The id of the Author of the Book."),
                                fieldWithPath("authors.[].firstName").description("The first name  of the Author."),
                                fieldWithPath("authors.[].lastName").description("The last name  of the Author."),
                                fieldWithPath("authors.[].emails.[]").description("The array of emails of the Author."),
                                fieldWithPath("authors.[].emails.[].id").description("The id of email of the Author."),
                                fieldWithPath("authors.[].emails.[].email").description("The email of the Author."),
                                fieldWithPath("authors.[].emails.[].emailType").description("The email type of the email."),
                                fieldWithPath("authors.[].phoneNumbers.[]").description("The array of phone numbers of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].id").description("The id of the phone number of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].phoneNumber").description("The phone number of the Author."),
                                fieldWithPath("authors.[].phoneNumbers.[].countryCode").description("The country code of the phone number."),
                                fieldWithPath("authors.[].phoneNumbers.[].type").description("The type of the phone number."),
                                fieldWithPath("authors.[].addresses.[]").description("The array of addresses of the Author."),
                                fieldWithPath("authors.[].addresses.[].id").description("The id of address of the Author."),
                                fieldWithPath("authors.[].addresses.[].country").description("The country of the Author."),
                                fieldWithPath("authors.[].addresses.[].city").description("The city of the Author."),
                                fieldWithPath("authors.[].addresses.[].street").description("The street of the Author."),
                                fieldWithPath("authors.[].addresses.[].houseNumber").description("The house number of the Author."),
                                fieldWithPath("authors.[].addresses.[].postcode").description("The postcode of the Author."),
                                fieldWithPath("authors.[].birthday").description("The birthday of the Author."),
                                fieldWithPath("authors.[].genres.[]").description("The array of genres of the Author."),
                                fieldWithPath("authors.[].genres.[].id").description("The id of the genre of the Author."),
                                fieldWithPath("authors.[].genres.[].name").description("The name of the genre of the Author.")))
                );
    }

    @Test
    public void getById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '0' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '-1' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getById_NotFoundBookById() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAll() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "          ";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{startPage}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)));
                .andDo(print())
                .andDo(document("books/get-all-by-pagination",
                        pathParameters(
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")),
                        responseFields(
                                fieldWithPath("content").description("Object that represent JPA pagination."),
                                fieldWithPath("content.[]").description("Array of the values Book that represent page."),
                                fieldWithPath("content.[].id").description("The id of Book."),
                                fieldWithPath("content.[].name").description("The name of Book."),
                                fieldWithPath("content.[].genres.[]").description("The array of Genre of the Book."),
                                fieldWithPath("content.[].genres.[].id").description("The id of Genre."),
                                fieldWithPath("content.[].genres.[].name").description("The name of the genre of the Book."),
                                fieldWithPath("content.[].publishingHouse").description("The Publishing House of the Book."),
                                fieldWithPath("content.[].publishingHouse.id").description("The id of Publishing House."),
                                fieldWithPath("content.[].publishingHouse.name").description("The name of the Publishing House of the Book."),
                                fieldWithPath("content.[].publishingHouse.address").description("The Address of the Publication House."),
                                fieldWithPath("content.[].publishingHouse.address.id").description("The id of Address."),
                                fieldWithPath("content.[].publishingHouse.address.country").description("The name of the country where located Publishing House."),
                                fieldWithPath("content.[].publishingHouse.address.city").description("The name of the city where located Publishing House."),
                                fieldWithPath("content.[].publishingHouse.address.street").description("The name of the street where located Publishing House."),
                                fieldWithPath("content.[].publishingHouse.address.houseNumber").description("The house number where located Publishing House."),
                                fieldWithPath("content.[].publishingHouse.address.postcode").description("The postcode where located Publishing House."),
                                fieldWithPath("content.[].publishingHouse.phoneNumbers.[]").description("The array of the phone numbers of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.phoneNumbers.[].id").description("The id of the phone number of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.phoneNumbers.[].phoneNumber").description("The phone number of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.phoneNumbers.[].countryCode").description("The country code of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.phoneNumbers.[].type").description("The type of the phone number of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.emails.[]").description("The array of emails of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.emails.[].id").description("The id of the email of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.emails.[].email").description("The email of the Publishing House."),
                                fieldWithPath("content.[].publishingHouse.emails.[].emailType").description("The email type of the Publishing House."),
                                fieldWithPath("content.[].publicationDate").description("The publicarion date of the Book."),
                                fieldWithPath("content.[].pathFile").description("The path file of the Book."),
                                fieldWithPath("content.[].comments.[]").description("The array of comments of the Book."),
                                fieldWithPath("content.[].comments.[].id").description("The id of the comment of the Book."),
                                fieldWithPath("content.[].comments.[].userId").description("The id of use of the comment of the Book."),
                                fieldWithPath("content.[].comments.[].text").description("The text of the comment of the Book."),
                                fieldWithPath("content.[].comments.[].date").description("The date of the comment of the Book."),
                                fieldWithPath("content.[].comments.[].subComments.[]").description("The array of subComments of the Comment."),
                                fieldWithPath("content.[].comments.[].subComments.[].id").description("The id of the subComment of the Comment."),
                                fieldWithPath("content.[].comments.[].subComments.[].userId").description("The id of user of the SubComment."),
                                fieldWithPath("content.[].comments.[].subComments.[].text").description("The text of the SubComment."),
                                fieldWithPath("content.[].comments.[].subComments.[].date").description("The date of the SubComment."),
                                fieldWithPath("content.[].authors.[]").description("The array of the Authors of the Book."),
                                fieldWithPath("content.[].authors.[].id").description("The id of the Author of the Book."),
                                fieldWithPath("content.[].authors.[].firstName").description("The first name  of the Author."),
                                fieldWithPath("content.[].authors.[].lastName").description("The last name  of the Author."),
                                fieldWithPath("content.[].authors.[].emails.[]").description("The array of emails of the Author."),
                                fieldWithPath("content.[].authors.[].emails.[].id").description("The id of email of the Author."),
                                fieldWithPath("content.[].authors.[].emails.[].email").description("The email of the Author."),
                                fieldWithPath("content.[].authors.[].emails.[].emailType").description("The email type of the email."),
                                fieldWithPath("content.[].authors.[].phoneNumbers.[]").description("The array of the phone numbers of the Author."),
                                fieldWithPath("content.[].authors.[].phoneNumbers.[].id").description("The id of the phone number of the Author."),
                                fieldWithPath("content.[].authors.[].phoneNumbers.[].phoneNumber").description("The phone number of the Author."),
                                fieldWithPath("content.[].authors.[].phoneNumbers.[].countryCode").description("The country code of the phone number."),
                                fieldWithPath("content.[].authors.[].phoneNumbers.[].type").description("The type of the phone number."),
                                fieldWithPath("content.[].authors.[].addresses.[]").description("The array of addresses of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].id").description("The id of address of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].country").description("The country of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].city").description("The city of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].street").description("The street of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].houseNumber").description("The house number of the Author."),
                                fieldWithPath("content.[].authors.[].addresses.[].postcode").description("The postcode of the Author."),
                                fieldWithPath("content.[].authors.[].birthday").description("The birthday of the Author."),
                                fieldWithPath("content.[].authors.[].genres.[]").description("The array of the genres of the Author."),
                                fieldWithPath("content.[].authors.[].genres.[].id").description("The id of the genre of the Author."),
                                fieldWithPath("content.[].authors.[].genres.[].name").description("The name of the genre of the Author."),
                                fieldWithPath("pageable.sort.sorted").ignored(),
                                fieldWithPath("pageable.sort.unsorted").ignored(),
                                fieldWithPath("pageable.sort.empty").ignored(),
                                fieldWithPath("pageable.pageSize").ignored(),
                                fieldWithPath("pageable.pageNumber").ignored(),
                                fieldWithPath("pageable.offset").ignored(),
                                fieldWithPath("pageable.paged").ignored(),
                                fieldWithPath("pageable.unpaged").ignored(),
                                fieldWithPath("totalPages").ignored(),
                                fieldWithPath("totalElements").ignored(),
                                fieldWithPath("last").ignored(),
                                fieldWithPath("first").ignored(),
                                fieldWithPath("sort.sorted").ignored(),
                                fieldWithPath("sort.unsorted").ignored(),
                                fieldWithPath("sort.empty").ignored(),
                                fieldWithPath("number").ignored(),
                                fieldWithPath("numberOfElements").ignored(),
                                fieldWithPath("size").ignored(),
                                fieldWithPath("empty").ignored())
                ));
    }

    @Sql(value = {"/Book_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getAll_NotFoundBook() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Books not found.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/0/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllAndSort() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
//        final String responseBodyContent = "{\"content\":[{\"id\":2,\"name\":\"AAAAAAAAAA\",\"genres\":[{\"id\":3,\"name\":\"Genre Book Uno\"},{\"id\":4,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":2,\"name\":\"Publication House\",\"address\":{\"id\":2,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":3,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":4,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":3,\"email\":\"email_1_publicationHouse92@email.com\",\"emailType\":\"Office\"},{\"id\":4,\"email\":\"email_2_publicationHouse92@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"30.10.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":2,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"02.11.2019\",\"subComments\":[{\"id\":2,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]}],\"authors\":[{\"id\":2,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":3,\"email\":\"email_1.author_194@email.com\",\"emailType\":\"Personal\"},{\"id\":4,\"email\":\"email_2.author_194@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":3,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":4,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":3,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":4,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"30.10.2019\",\"genres\":[{\"id\":3,\"name\":\"Genre Uno\"},{\"id\":4,\"name\":\"Genre Dos\"}]}]},{\"id\":1,\"name\":\"BBBBBBBBB\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"30.10.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment\",\"date\":\"02.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"30.10.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}]}],\"pageable\":{\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"pageSize\":2,\"pageNumber\":0,\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":2,\"last\":true,\"first\":true,\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"size\":2,\"number\":0,\"numberOfElements\":2,\"empty\":false}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/0/2/name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)));
                .andDo(print());
    }

    @Sql(value = {"/Book_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getAllAndSort_NotFoundBook() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Books not found.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/1/name\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/0/1/name"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("books/delete-by-id",
                        pathParameters(
                                parameterWithName("bookId").description("By this 'bookId' will be deleted Book"))
                ));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '0' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Parameter '-1' is illegal, check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Book_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Book_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById_BookByIdNotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}