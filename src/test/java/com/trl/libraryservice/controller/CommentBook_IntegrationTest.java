package com.trl.libraryservice.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
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
public class CommentBook_IntegrationTest {

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

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {
        // TODO: How mock webclient, which uses this method.

        final String responseBodyContent = "{\"commentId\":4,\"userId\":1,\"text\":\"new comment added\",\"date\":\"01.01.2000\",\"subComments\":[],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/1/comments\"},\"getByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4\"},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4\"},\"deleteByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments\"},\"addSubComment\":{\"href\":\"http://localhost:8080/books/comments/4/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/4/subComments\"}}}";
        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"01.01.2000\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/add",
                        pathParameters(
                                parameterWithName("bookId").description("The book id to which comments will be added. .")
                        ),
                        requestFields(
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment.")
                        ),
                        responseFields(
                                fieldWithPath("commentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("_links.self.href").description("Link to self(add) the comment resource."),
                                fieldWithPath("_links.getByCommentId.href").description("Link to get the comment resource by comment id."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.href").description("Link to get the page of comments resource by book id."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.href").description("Link to get the page of sorted comments resource by book id."),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.updateByCommentId.href").description("Link to upgrade comment resource by comment id."),
                                fieldWithPath("_links.deleteByCommentId.href").description("Link to delete comment resource by comment id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.href").description("Link to delete comments resource by book id."),
                                fieldWithPath("_links.addSubComment.href").description("Link to add sub comments to the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comments from the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get page of sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get page of sorted sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to update sub comments from the comment resource."),
                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comments from the comment resource."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete all sub comments from the comment resource.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(add) the comment resource."),
                                linkWithRel("getByCommentId").description("Link to get the comment resource by comment id."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource by book id."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource by book id."),
                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
                                linkWithRel("deleteByCommentId").description("Link to delete comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id."),
                                linkWithRel("addSubComment").description("Link to add sub comments to the comment resource."),
                                linkWithRel("getBySubCommentId").description("Link to get sub comments from the comment resource."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get page of sub comments from the comment resource."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get page of sorted sub comments from the comment resource."),
                                linkWithRel("updateBySubCommentId").description("Link to update sub comments from the comment resource."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comments from the comment resource."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete all sub comments from the comment resource.")
                        )
                ));

        // Check if comment is add correctly.
        final String responseBodyContent_2 = "{\"bookId\":1,\"name\":\"My first Book\",\"genres\":[{\"id\":1,\"name\":\"Genre Book Uno\"},{\"id\":2,\"name\":\"Genre Book Dos\"}],\"publishingHouse\":{\"id\":1,\"name\":\"Publication House\",\"address\":{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":1111},\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"0111111111111111\",\"countryCode\":\"11111\",\"type\":\"Office\"},{\"id\":2,\"phoneNumber\":\"02222222222222\",\"countryCode\":\"222222\",\"type\":\"Fax\"}],\"emails\":[{\"id\":1,\"email\":\"email_1_publicationHouse9@email.com\",\"emailType\":\"Office\"},{\"id\":2,\"email\":\"email_2_publicationHouse9@email.com\",\"emailType\":\"Office 2\"}]},\"publicationDate\":\"30.10.2019\",\"pathFile\":\"path/path/book\",\"comments\":[{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]},{\"commentId\":2,\"userId\":1,\"text\":\"Text Comment Two\",\"date\":\"02.11.2019\",\"subComments\":[]},{\"commentId\":3,\"userId\":1,\"text\":\"Text Comment Three\",\"date\":\"03.11.2019\",\"subComments\":[]},{\"commentId\":4,\"userId\":1,\"text\":\"new comment added\",\"date\":\"01.01.2000\",\"subComments\":[]}],\"authors\":[{\"id\":1,\"firstName\":\"Author_1 FirstName\",\"lastName\":\"Author_1 LastName\",\"emails\":[{\"id\":1,\"email\":\"email_1.author_19@email.com\",\"emailType\":\"Personal\"},{\"id\":2,\"email\":\"email_2.author_19@email.com\",\"emailType\":\"Personal\"}],\"phoneNumbers\":[{\"id\":1,\"phoneNumber\":\"01111111111111\",\"countryCode\":\"111\",\"type\":\"Personal\"},{\"id\":2,\"phoneNumber\":\"0222222222222222\",\"countryCode\":\"222\",\"type\":\"Work\"}],\"addresses\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Uno\",\"houseNumber\":\"1A\",\"postcode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Dos\",\"houseNumber\":\"2A\",\"postcode\":222222}],\"birthday\":\"30.10.2019\",\"genres\":[{\"id\":1,\"name\":\"Genre Uno\"},{\"id\":2,\"name\":\"Genre Dos\"}]}],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/{id}\",\"templated\":true},\"add\":{\"href\":\"http://localhost:8080/books\"},\"getPageOfBooks\":{\"href\":\"http://localhost:8080/books/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBooks\":{\"href\":\"http://localhost:8080/books/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"update\":{\"href\":\"http://localhost:8080/books/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/books/1\"},\"addComment\":{\"href\":\"http://localhost:8080/books/1/comments\"},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/1/comments\"}}}";
        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void add_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 0)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", -1)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalParrameterCommntBook_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":null,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueZero() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":0,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueZero_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":-1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_TextVaule_Null() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":1,\"text\":null,\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_TextValue_Empty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' is empty', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":1,\"text\":\"\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_Date() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''date' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":1,\"text\":\"Comment Message added.\",\"date\":null,\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_BookByBookId_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_User_ByUserId_NotExist() throws Exception {
        // TODO: How mock webclient, which uses this method.

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User by this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";
        this.mockMvc.perform(
                post(BASE_URL + "/books/{bookId}/comments", 1)
                        .content("{\"userId\":100,\"text\":\"new comment added\",\"date\":\"01.01.2000\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByCommentId() throws Exception {

        final String responseBodyContent = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"addSubComment\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/getByCommentId",
                        pathParameters(
                                parameterWithName("commentId").description("The commentId used to search for a comment.")
                        ),
                        responseFields(
                                fieldWithPath("commentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("subComments.[].subCommentId").description("The id of sub comment."),
                                fieldWithPath("subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("subComments.[].date").description("Date of the sub comment."),
                                fieldWithPath("_links.self.href").description("Link to self(getBYCommentId) the comment resource by id."),
                                fieldWithPath("_links.add.href").description("Link to add the comment resource."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getPageOfCommentsByBookId.href").description("Link to get the page of comments resource."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.href").description("Link to get the page of sorted comments resource."),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.updateByCommentId.href").description("Link to upgrade comment resource by comment id."),
                                fieldWithPath("_links.deleteByCommentId.href").description("Link to delete comment resource by comment id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.href").description("Link to delete comments resource by book id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.addSubComment.href").description("Link to add sub comments to the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comments from the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get page of sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get page of sorted sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to update sub comments from the comment resource."),
                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comments from the comment resource."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete all sub comments from the comment resource.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(getBYCommentId) the comment resource by id."),
                                linkWithRel("add").description("Link to add the comment resource."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource."),
                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
                                linkWithRel("deleteByCommentId").description("Link to delete comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id."),
                                linkWithRel("addSubComment").description("Link to add sub comments to the comment resource."),
                                linkWithRel("getBySubCommentId").description("Link to get sub comments from the comment resource."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get page of sub comments from the comment resource."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get page of sorted sub comments from the comment resource."),
                                linkWithRel("updateBySubCommentId").description("Link to update sub comments from the comment resource."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comments from the comment resource."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete all sub comments from the comment resource.")
                        )
                ));
    }

    @Test
    public void getByCommentId_IllegalCommentId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getByCommentId_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getByCommentId_IllegalCommentId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByCommentId_Comment_NotFound() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 33))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfCommentsByBookId() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "        ";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/getPageOfCommentsByBookId",
                        pathParameters(
                                parameterWithName("bookId").description("The bookId used to search for a comments."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")
                        ),
                        responseFields(
                                fieldWithPath("content.[].commentId").description("The id of Comment"),
                                fieldWithPath("content.[].userId").description("The user id of the user who created this comment."),
                                fieldWithPath("content.[].text").description("Text of the comment."),
                                fieldWithPath("content.[].date").description("Date of the comment."),
                                fieldWithPath("content.[].subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("content.[].subComments.[].subCommentId").description("The id of sub comment."),
                                fieldWithPath("content.[].subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].subComments.[].date").description("Date of the sub comment."),
                                fieldWithPath("content.[].subComments.[].links.[]").ignored(),
                                fieldWithPath("content.[].links[].href").ignored(),
                                fieldWithPath("content.[].links[].rel").ignored(),
                                fieldWithPath("content.[].links[].hreflang").ignored(),
                                fieldWithPath("content.[].links[].media").ignored(),
                                fieldWithPath("content.[].links[].title").ignored(),
                                fieldWithPath("content.[].links[].type").ignored(),
                                fieldWithPath("content.[].links[].deprecation").ignored(),
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
                                fieldWithPath("empty").ignored()
                        ),
                        links(
                                halLinks()
                        )
                ));
    }

    @Test
    public void getPageOfCommentsByBookId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfCommentsByBookId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 0, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfCommentsByBookId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", -1, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfCommentsByBookId_BookById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/33/comments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 33, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfCommentsByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedCommentsByBookId() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "     ";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}", 1, 0, 2, "date"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/getPageOfSortedCommentsByBookId",
                        pathParameters(
                                parameterWithName("bookId").description("The bookId used to search for a comments."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0"),
                                parameterWithName("sortOrder").description("The value by which the sorted comments will be.")
                        ),
                        responseFields(
                                fieldWithPath("content.[].commentId").description("The id of Comment"),
                                fieldWithPath("content.[].userId").description("The user id of the user who created this comment."),
                                fieldWithPath("content.[].text").description("Text of the comment."),
                                fieldWithPath("content.[].date").description("Date of the comment."),
                                fieldWithPath("content.[].subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("content.[].subComments.[].subCommentId").description("The id of sub comment."),
                                fieldWithPath("content.[].subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].subComments.[].date").description("Date of the sub comment."),
                                fieldWithPath("content.[].subComments.[].links.[]").ignored(),
                                fieldWithPath("content.[].links[].href").ignored(),
                                fieldWithPath("content.[].links[].rel").ignored(),
                                fieldWithPath("content.[].links[].hreflang").ignored(),
                                fieldWithPath("content.[].links[].media").ignored(),
                                fieldWithPath("content.[].links[].title").ignored(),
                                fieldWithPath("content.[].links[].type").ignored(),
                                fieldWithPath("content.[].links[].deprecation").ignored(),
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
                                fieldWithPath("empty").ignored()
                        ),
                        links(
                                halLinks()
                        )
                ));
    }

    @Test
    public void getPageOfSortedCommentsByBookId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfSortedCommentsByBookId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}", 0, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSortedCommentsByBookId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}", -1, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedCommentsByBookId_BookById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/33/comments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}", 33, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedCommentsByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}", 1, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void update_docs() throws Exception {

        final Integer COMMENT_ID = 1;

        final String responseBodyContent = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"deleteByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"addSubComment\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"}}}";

        this.mockMvc.perform(
                patch(BASE_URL + "/books/comments/{commentId}", COMMENT_ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"01.01.2000\",\"subComments\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/update",
                        pathParameters(
                                parameterWithName("commentId").description("Comment with this commentId to be updated.")
                        ),
                        requestFields(
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment.")
                        ),
                        responseFields(
                                fieldWithPath("commentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("subComments.[].subCommentId").description("The id of sub comment."),
                                fieldWithPath("subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("subComments.[].date").description("Date of the sub comment."),
                                fieldWithPath("_links.self.href").description("Link to self(updateByCommentId) the comment resource."),
                                fieldWithPath("_links.add.href").description("Link to add comment resource by book id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByCommentId.href").description("Link to get the comment resource by comment id."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.href").description("Link to get the page of comments resource by book id."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.href").description("Link to get the page of sorted comments resource by book id."),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.deleteByCommentId.href").description("Link to delete comment resource by comment id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.href").description("Link to delete comments resource by book id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.addSubComment.href").description("Link to add sub comments to the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comments from the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get page of sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get page of sorted sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to update sub comments from the comment resource."),
                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comments from the comment resource."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete all sub comments from the comment resource.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(updateByCommentId) the comment resource."),
                                linkWithRel("add").description("Link to add comment resource by book id."),
                                linkWithRel("getByCommentId").description("Link to get the comment resource by comment id."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource by book id."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource by book id."),
                                linkWithRel("deleteByCommentId").description("Link to delete comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id."),
                                linkWithRel("addSubComment").description("Link to add sub comments to the comment resource."),
                                linkWithRel("getBySubCommentId").description("Link to get sub comments from the comment resource."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get page of sub comments from the comment resource."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get page of sorted sub comments from the comment resource."),
                                linkWithRel("updateBySubCommentId").description("Link to update sub comments from the comment resource."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comments from the comment resource."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete all sub comments from the comment resource.")
                        )
                ));
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByCommentId() throws Exception {

        final String responseBodyContent = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"addSubComment\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"}}}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/deleteByCommentId",
                        pathParameters(
                                parameterWithName("commentId").description("The commentId used to search for a comment.")
                        ),
                        responseFields(
                                fieldWithPath("commentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("_links.self.href").description("Link to self(deleteByCommentId) the comment resource by id."),
                                fieldWithPath("_links.add.href").description("Link to add the comment resource."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByCommentId.href").description("Link to get comment resource by comment id."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.href").description("Link to get the page of comments resource."),
                                fieldWithPath("_links.getPageOfCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.href").description("Link to get the page of sorted comments resource."),
                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.updateByCommentId.href").description("Link to upgrade comment resource by comment id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.href").description("Link to delete comments resource by book id."),
                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored(),
                                fieldWithPath("_links.addSubComment.href").description("Link to add sub comments to the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comments from the comment resource."),
                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get page of sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get page of sorted sub comments from the comment resource."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to update sub comments from the comment resource."),
                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comments from the comment resource."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete all sub comments from the comment resource.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(deleteByCommentId) the comment resource by id."),
                                linkWithRel("add").description("Link to add the comment resource."),
                                linkWithRel("getByCommentId").description("Link to get comment resource by comment id."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource."),
                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id."),
                                linkWithRel("addSubComment").description("Link to add sub comments to the comment resource."),
                                linkWithRel("getBySubCommentId").description("Link to get sub comments from the comment resource."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get page of sub comments from the comment resource."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get page of sorted sub comments from the comment resource."),
                                linkWithRel("updateBySubCommentId").description("Link to update sub comments from the comment resource."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comments from the comment resource."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete all sub comments from the comment resource.")
                        )
                ));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteByCommentId_IllegalId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteByCommentId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteByCommentId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByCommentId_Comment_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllCommentsByBookId() throws Exception {

        final String responseBodyContent = "[{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"addSubComment\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteAllSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"commentId\":2,\"userId\":1,\"text\":\"Text Comment Two\",\"date\":\"02.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"addSubComment\",\"href\":\"http://localhost:8080/books/comments/2/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/2/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/2/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteAllSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/2/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"commentId\":3,\"userId\":1,\"text\":\"Text Comment Three\",\"date\":\"03.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"addSubComment\",\"href\":\"http://localhost:8080/books/comments/3/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/3/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/3/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteAllSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/3/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]}]";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("comments/deleteAllCommentsByBookId",
                        pathParameters(
                                parameterWithName("bookId").description("The bookId used to search for a comment.")
                        ),
                        responseFields(
                                fieldWithPath("[].commentId").description("The id of Comment"),
                                fieldWithPath("[].userId").description("The user id of the user who created this comment."),
                                fieldWithPath("[].text").description("Text of the comment."),
                                fieldWithPath("[].date").description("Date of the comment."),
                                fieldWithPath("[].subComments.[]").description("The array of sub comments by the comment."),
                                fieldWithPath("[].links[].rel").ignored(),
                                fieldWithPath("[].links[].href").ignored(),
                                fieldWithPath("[].links[].hreflang").ignored(),
                                fieldWithPath("[].links[].media").ignored(),
                                fieldWithPath("[].links[].title").ignored(),
                                fieldWithPath("[].links[].type").ignored(),
                                fieldWithPath("[].links[].deprecation").ignored()
                        )
                ));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments/0/10\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 10))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteAllCommentsByBookId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteAllCommentsByBookId_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllCommentsByBookId_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllCommentsByBookId_BookById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/33/comments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", 33))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllCommentsByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}
