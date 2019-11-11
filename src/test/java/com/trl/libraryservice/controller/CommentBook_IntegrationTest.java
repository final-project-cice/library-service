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
public class CommentBook_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

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
        // TODO: Finish this test.
    }

    @Test
    public void add_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/0/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/-1/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_IllegalParrameterCommntBook_NullValue() throws Exception {
        // TODO: Busar laiforcion como se pede pasar valor null, y terminar ese test.
        /*String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("null")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());*/
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":null,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueZero() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":0,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_UserId_ValueZero_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":-1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_Text() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":null,\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());

        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"Field ''text' is empty', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterCommntBook_IllegallFild_Date() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''date' not be equals to null', check the field that it has the 'commentBook' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"Comment Message added.\",\"date\":null,\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_BookByBookId_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/1/comments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
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
                get("http://localhost:8082/books/comments/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void getById_IllegalCommentId_NullValue() throws Exception {

        // TODO: Finish this test.
        /*String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "       ";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());*/
    }

    @Test
    public void getById_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getById_IllegalCommentId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById_Comment_NotFound() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/33"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByBookId() throws Exception {

        final String responseBodyContent = "[{\"id\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}]},{\"id\":2,\"userId\":1,\"text\":\"Text Comment Two\",\"date\":\"02.11.2019\",\"subComments\":[]},{\"id\":3,\"userId\":1,\"text\":\"Text Comment Three\",\"date\":\"03.11.2019\",\"subComments\":[]}]";

        this.mockMvc.perform(
                get("http://localhost:8082/books/1/comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void getAllByBookId_IllegalBookId_NullValue() throws Exception {

        // TODO: Finish this test.
        /*String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "       ";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());*/
    }

    @Test
    public void getAllByBookId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllByBookId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/-1/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByBookId_BookById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/33/comments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/33/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/1/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/1"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteById_IllegalId_NullValue() throws Exception {

        // TODO: Finish this test.
        /*String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "       ";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());*/
    }

    @Test
    public void deleteById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById_Comment_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/1/comments"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                get("http://localhost:8082/books/1/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAllByBookId_IllegalBookId_NullValue() throws Exception {

        // TODO: Finish this test.
        /*String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "       ";

        this.mockMvc.perform(
                get("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());*/
    }

    @Test
    public void deleteAllByBookId_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/0/comments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/0/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAllByBookId_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/-1/comments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/-1/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAllByBookId_BookById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Book with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/33/comments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/33/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Comments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllByBookId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comments with this bookId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/1/comments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/1/comments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }
}
