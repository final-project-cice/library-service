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
public class SubCommentComment_IntegrationTest {

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

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {
        // TODO: How mock webclient, which uses this method.

        final String responseBodyContent = "{\"subCommentId\":4,\"userId\":1,\"text\":\"new sub comment added\",\"date\":\"01.01.2000\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"}}}";
        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":1,\"text\":\"new sub comment added\",\"date\":\"01.01.2000\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/add",
                        pathParameters(
                                parameterWithName("commentId").description("The comment id to which subComments will be added. .")
                        ),
                        requestFields(
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment.")
                        ),
                        responseFields(
                                fieldWithPath("subCommentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("_links.self.href").description("Link to self(add) the sub comment resource."),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get the sub comment resource by sub comment id."),
                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get the page of sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get the page of sorted sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to upgrade sub comment resource by sub comment id."),
                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comment resource by sub comment id."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete sub comments resource by comment id.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(add) the sub comment resource."),
                                linkWithRel("getBySubCommentId").description("Link to get the sub comment resource by sub comment id."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get the page of sub comments resource by comment id."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get the page of sorted sub comments resource by comment id."),
                                linkWithRel("updateBySubCommentId").description("Link to upgrade sub comment resource by sub comment id."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comment resource by sub comment id."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete sub comments resource by comment id.")
                        )
                ));

        // Check if subComment is add correctly.
        final String responseBodyContent_2 = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\"},{\"subCommentId\":2,\"userId\":1,\"text\":\"test sub comment Second\",\"date\":\"02.11.2019\"},{\"subCommentId\":3,\"userId\":1,\"text\":\"test sub comment Three\",\"date\":\"03.11.2019\"},{\"subCommentId\":4,\"userId\":1,\"text\":\"new sub comment added\",\"date\":\"01.01.2000\"}],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"addSubComment\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1/subComments\"}}}";
        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
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
    public void add_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 0)
                        .content("{\"userId\":1,\"text\":\"new sub comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", -1)
                        .content("{\"userId\":1,\"text\":\"new sub comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalParrameterSubCommnt_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":null,\"text\":\"new sub comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_ValueZero() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":0,\"text\":\"new comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_UserId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''userId' must be greater that zero', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":-1,\"text\":\"new comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Text_ValueNull() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":1,\"text\":null,\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Text_ValueEmpty() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''text' is empty', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":1,\"text\":\"\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParrameterSubCommnt_IllegallFild_Date() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field ''date' not be equals to null', check the field that it has the 'subComment' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":1,\"text\":\"Comment Message added.\",\"date\":null}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_Comment_ByCommentId_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":1,\"text\":\"new comment added\",\"date\":\"12.06.2019\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_User_ByUserId_NotExist() throws Exception {
        // TODO: How mock webclient, which uses this method.

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User by this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";
        this.mockMvc.perform(
                post(BASE_URL + "/books/comments/{commentId}/subComments", 1)
                        .content("{\"userId\":100,\"text\":\"new comment added\",\"date\":\"01.01.2000\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getBySubCommentId() throws Exception {

        final String responseBodyContent = "{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/subComments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments\",\"templated\":true},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/1\"},\"deleteBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"templated\":true},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments\",\"templated\":true}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/subComments/{subCommentId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/getBySubCommentId",
                        pathParameters(
                                parameterWithName("subCommentId").description("The subCommentId used to search for a subComment.")
                        ),
                        responseFields(
                                fieldWithPath("subCommentId").description("The id of Comment"),
                                fieldWithPath("userId").description("The user id of the user who created this comment."),
                                fieldWithPath("text").description("Text of the comment."),
                                fieldWithPath("date").description("Date of the comment."),
                                fieldWithPath("_links.self.href").description("Link to self(getBySubCommentId) the sub comment resource."),
                                fieldWithPath("_links.add.href").description("Link to add the sub comment resource by comment id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get the page of sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get the page of sorted sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to upgrade sub comment resource by sub comment id."),
                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comment resource by sub comment id."),
                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete sub comments resource by comment id."),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(getBySubCommentId) the sub comment resource."),
                                linkWithRel("add").description("Link to add the sub comment resource by comment id."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get the page of sub comments resource by comment id."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get the page of sorted sub comments resource by comment id."),
                                linkWithRel("updateBySubCommentId").description("Link to upgrade sub comment resource by sub comment id."),
                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comment resource by sub comment id."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete sub comments resource by comment id.")
                        )
                ));
    }

    @Test
    public void getBySubCommentId_IllegalCommentId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getBySubCommentId_IllegalCommentId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/subComments/{subCommentId}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getBySubCommentId_IllegalCommentId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/subComments/{subCommentId}", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getBySubCommentId_Comment_NotFound() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComment with this subCommentId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/33\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/subComments/{subCommentId}", 33))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSubCommentsByCommentId() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
//        final String responseBodyContent = "{\"content\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"pageSize\":1,\"pageNumber\":0,\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":3,\"totalElements\":3,\"last\":false,\"first\":true,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"number\":0,\"numberOfElements\":1,\"size\":1,\"empty\":false}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", 1, 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/getPageOfSubCommentsByCommentId",
                        pathParameters(
                                parameterWithName("commentId").description("The commentId used to search for a sub comments."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")
                        ),
                        responseFields(
                                fieldWithPath("content.[].subCommentId").description("The id of sub comment"),
                                fieldWithPath("content.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].date").description("Date of the sub comment."),
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
    public void getPageOfSubCommentsByCommentId_IllegalId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfSubCommentsByCommentId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", 0, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSubCommentsByCommentId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", -1, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSubCommentsByCommentId_CommentById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33/subComments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", 33, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSubCommentsByCommentId_NotFoundSubComments_ByCommentId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments/0/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", 1, 0, 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedSubCommentsByCommentId() throws Exception {

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
//        final String responseBodyContent = "{\"content\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"pageSize\":1,\"pageNumber\":0,\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":3,\"totalElements\":3,\"last\":false,\"first\":true,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"number\":0,\"numberOfElements\":1,\"size\":1,\"empty\":false}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}", 1, 0, 1, "date"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/getPageOfSortedSubCommentsByCommentId",
                        pathParameters(
                                parameterWithName("commentId").description("The commentId used to search for a sub comments."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0"),
                                parameterWithName("sortOrder").description("The value by which the sorted comments will be.")
                        ),
                        responseFields(
                                fieldWithPath("content.[].subCommentId").description("The id of sub comment"),
                                fieldWithPath("content.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].date").description("Date of the sub comment."),
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
    public void getPageOfSortedSubCommentsByCommentId_IllegalId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfSortedSubCommentsByCommentId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}", 0, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSortedSubCommentsByCommentId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}", -1, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedSubCommentsByCommentId_CommentById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33/subComments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}", 33, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedSubCommentsByCommentId_NotFoundSubComments_ByCommentId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments/0/1/date\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}", 1, 0, 1, "date"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateBySubCommentId() throws Exception {
        // TODO: Finish this test.
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteBySubCommentId() throws Exception {

        final String responseBodyContent = "{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/subComments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments\",\"templated\":true},\"getBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/1\"},\"getPageOfSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateBySubCommentId\":{\"href\":\"http://localhost:8080/books/comments/subComments/1\"},\"deleteAllSubCommentsByCommentId\":{\"href\":\"http://localhost:8080/books/comments/{commentId}/subComments\",\"templated\":true}}}";
        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/subComments/{subCommentId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/deleteBySubCommentId",
                        pathParameters(
                                parameterWithName("subCommentId").description("The subCommentId used to delete a subComment.")
                        ),
                        responseFields(
                                fieldWithPath("subCommentId").description("The id of sub comment"),
                                fieldWithPath("userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("text").description("Text of the sub comment."),
                                fieldWithPath("date").description("Date of the sub comment."),
                                fieldWithPath("_links.self.href").description("Link to self(deleteBySubComment) the sub comment resource."),
                                fieldWithPath("_links.add.href").description("Link to add the sub comment resource by comment id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comment resource by sub comment id."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get the page of sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get the page of sorted sub comments resource by comment id."),
                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to upgrade sub comment resource by sub comment id."),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete sub comments resource by comment id."),
                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(deleteBySubCommentId) the sub comment resource."),
                                linkWithRel("add").description("Link to add the sub comment resource by comment id."),
                                linkWithRel("getBySubCommentId").description("Link to get sub comment resource by sub comment id."),
                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get the page of sub comments resource by comment id."),
                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get the page of sorted sub comments resource by comment id."),
                                linkWithRel("updateBySubCommentId").description("Link to upgrade sub comment resource by sub comment id."),
                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete sub comments resource by comment id.")
                        )
                ));

        // Check if subComment is deleted corectly.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"SubComment with this subCommentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/1\"}";
        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/subComments/{subCommentId}", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteBySubCommentId_IllegalId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteBySubCommentId_IllegalId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/subComments/{subCommentId}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteBySubCommentId_IllegalId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/-1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/subComments/{subCommentId}", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteBySubCommentId_SubComment_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComment with this subCommentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/subComments/1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/subComments/{subCommentId}", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubCommentComment_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllSubCommentsByCommentId() throws Exception {

        final String responseBodyContent = "[{\"subCommentId\":1,\"userId\":1,\"text\":\"test sub comment First\",\"date\":\"01.11.2019\",\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"subCommentId\":2,\"userId\":1,\"text\":\"test sub comment Second\",\"date\":\"02.11.2019\",\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"subCommentId\":3,\"userId\":1,\"text\":\"test sub comment Three\",\"date\":\"03.11.2019\",\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/comments/1/subComments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedSubCommentsByCommentId\",\"href\":\"http://localhost:8080/books/comments/1/subComments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteBySubCommentId\",\"href\":\"http://localhost:8080/books/comments/subComments/{subCommentId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]}]";
        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}/subComments", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("subComments/deleteAllSubCommentsByCommentId",
                        pathParameters(
                                parameterWithName("commentId").description("The commentId used to delete all subComments form this comment.")
                        ),
                        responseFields(
                                fieldWithPath("[].subCommentId").description("The id of sub comment"),
                                fieldWithPath("[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("[].text").description("Text of the sub comment."),
                                fieldWithPath("[].date").description("Date of the sub comment."),
                                fieldWithPath("[].links[].rel").ignored(),
                                fieldWithPath("[].links[].href").ignored(),
                                fieldWithPath("[].links[].hreflang").ignored(),
                                fieldWithPath("[].links[].media").ignored(),
                                fieldWithPath("[].links[].title").ignored(),
                                fieldWithPath("[].links[].type").ignored(),
                                fieldWithPath("[].links[].deprecation").ignored()
                        )
                ));

        // Check if subComments is deleted correctly.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments/0/10\"}";
        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}/subComments/{startPage}/{pageSize}", 1, 0, 10))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteAllSubCommentsByCommentId_IllegalBookId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteAllSubCommentsByCommentId_IllegalBookId_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/0/subComments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}/subComments", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllSubCommentsByCommentId_IllegalBookId_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/-1/subComments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}/subComments", -1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllSubCommentsByCommentId_SubCommentById_NotExist() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Comment with this id = 33 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/33/subComments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}/subComments", 33))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/SubComment_SubComments_NotFound_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/SubCommentComment_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllSubCommentsByCommentId_NotFoundSubComments_ByCommentId() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"SubComments with this commentId = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1/subComments\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/books/comments/{commentId}/subComments", 1))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}
