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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
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

    //    @MockBean
//    WebClientFactory webClientFactory;

    @Sql(value = {"/CommentBook_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {
        // TODO: how to use MockMvc to mock webclient.
        // TODO: Finish this test.
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
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":null,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":0,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":-1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":1,\"text\":null,\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":1,\"text\":\"\",\"date\":\"12.06.2019\",\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":1,\"text\":\"Comment Message added.\",\"date\":null,\"subComments\":[]}")
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
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_User_ByUserId_NotExist() throws Exception {
        // TODO: how to use MockMvc to mock webclient.
        // TODO: Finish this test.
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {

        final String responseBodyContent = "{\"id\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getById_IllegalCommentId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getById_IllegalCommentId_ZeroValue() throws Exception {

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
    public void getById_IllegalCommentId_NegativeValue() throws Exception {

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
    public void getById_Comment_NotFound() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 33))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByBookId() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
//        final String responseBodyContent = "{\"content\":[{\"id\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]},{\"id\":2,\"userId\":1,\"text\":\"Text Comment Two\",\"date\":\"02.11.2019\",\"subComments\":[]}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"pageSize\":2,\"pageNumber\":0,\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":2,\"totalElements\":3,\"last\":false,\"first\":true,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"size\":2,\"number\":0,\"numberOfElements\":2,\"empty\":false}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getAllByBookId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getAllByBookId_IllegalId_ZeroValue() throws Exception {

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
    public void getAllByBookId_IllegalId_NegativeValue() throws Exception {

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
    public void getAllByBookId_BookById_NotExist() throws Exception {

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
    public void getAllByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isOk())
                .andDo(print());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteById_IllegalId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteById_IllegalId_ZeroValue() throws Exception {

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
    public void deleteById_IllegalId_NegativeValue() throws Exception {

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
    public void deleteById_Comment_NotExist() throws Exception {

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
    public void deleteAllByBookId() throws Exception {

        this.mockMvc.perform(
                delete(BASE_URL + "/books/{bookId}/comments", 1))
                .andExpect(status().isOk())
                .andDo(print());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments/0/10\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/{bookId}/comments/{startPage}/{pageSize}", 1, 0, 10))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllByBookId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteAllByBookId_IllegalBookId_ZeroValue() throws Exception {

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
    public void deleteAllByBookId_IllegalBookId_NegativeValue() throws Exception {

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
    public void deleteAllByBookId_BookById_NotExist() throws Exception {

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
    public void deleteAllByBookId_NotFoundComments_ByBookId() throws Exception {

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
