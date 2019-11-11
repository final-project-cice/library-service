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
public class SubCommentComment_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/SubCommentComment_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
    public void add_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/0/subComments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/-1/subComments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_IllegalParrameterSubCommnt_NullValue() throws Exception {
        // TODO: Busar laiforcion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":null,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_ValueZero() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":0,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_NegativeValue() throws Exception {


        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":-1,\"text\":\"new comment added\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Text_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":1,\"text\":null,\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Text_ValueEmpty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' is empty', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"\",\"date\":\"12.06.2019\",\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Date() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''date' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
                        .content("{\"id\":null,\"userId\":1,\"text\":\"Comment Message added.\",\"date\":null,\"subComments\":[]}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_Comment_ByCommentId_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post("http://localhost:8082/books/comments/1/subComments")
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

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {

        final String responseBodyContent = "{\"id\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/subComments/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void getById_IllegalCommentId_NullValue() throws Exception {
        // TODO: Finish this test.
    }

    @Test
    public void getById_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/0\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/subComments/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getById_IllegalCommentId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/-1\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/subComments/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById_Comment_NotFound() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComment with this subCommentId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/33\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/subComments/33"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByCommentId() throws Exception {

        final String responseBodyContent = "[{\"id\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\"},{\"id\":2,\"userId\":1,\"text\":\"test sub comment Second\",\"date\":\"02.11.2019\"},{\"id\":3,\"userId\":1,\"text\":\"test sub comment Three\",\"date\":\"03.11.2019\"}]";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/1/subComments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseBodyContent)));
    }

    @Test
    public void getAllByCommentId_IllegalBookId_NullValue() throws Exception {
        // TODO: Finish this test.
    }

    @Test
    public void getAllByCommentId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/0/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllByCommentId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/-1/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByCommentId_CommentById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33/subComments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/33/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getAllByCommentId_NotFoundComments_ByBookId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/1/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/subComments/1"))
                .andDo(print())
                .andExpect(status().isOk());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComment with this subCommentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/1\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/subComments/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteById_IllegalId_NullValue() throws Exception {
        // TODO: Finish this test.
    }

    @Test
    public void deleteById_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/0\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/subComments/0"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteById_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/-1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/subComments/-1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById_SubComment_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComment with this subCommentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/1\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/subComments/1"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllByCommentId() throws Exception {

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/1/subComments"))
                .andDo(print())
                .andExpect(status().isOk());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                get("http://localhost:8082/books/comments/1/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAllByCommentId_IllegalBookId_NullValue() throws Exception {
        // TODO: Finish this test.
    }

    @Test
    public void deleteAllByCommentId_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/0/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAllByCommentId_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/-1/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAllByCommentId_SubCommentById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33/subComments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/33/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllByCommentId_NotFoundSubComments_ByCommentId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                delete("http://localhost:8082/books/comments/1/subComments"))
                .andDo(print())
                .andExpect(content().string(containsString(responseBodyContent)))
                .andExpect(status().isBadRequest());
    }
}
