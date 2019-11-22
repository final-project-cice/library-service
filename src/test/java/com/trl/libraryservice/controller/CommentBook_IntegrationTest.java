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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

//    @Autowired
//    private MockWebServer mockWebServer;

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

    @Test
    public void add_User_ByUserId_NotExist() throws Exception {
        // TODO: how to use MockMvc to mock webclient.
        // TODO: Finish this test.
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByCommentId() throws Exception {

        final String responseBodyContent = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[{\"id\":1,\"userId\":1,\"text\":\"test sub comment\",\"date\":\"02.11.2019\"}],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true}}}";

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
                                fieldWithPath("subComments.[].id").description("The id of sub comment."),
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
                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(getBYCommentId) the comment resource by id."),
                                linkWithRel("add").description("Link to add the comment resource."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource."),
                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
                                linkWithRel("deleteByCommentId").description("Link to delete comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id.")
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
                .andExpect(status().isNotFound())
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
                                fieldWithPath("content.[].subComments.[].id").description("The id of sub comment."),
                                fieldWithPath("content.[].subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].subComments.[].date").description("Date of the sub comment."),
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
                .andExpect(status().isNotFound())
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
                                fieldWithPath("content.[].subComments.[].id").description("The id of sub comment."),
                                fieldWithPath("content.[].subComments.[].userId").description("The user id of the user who created this sub comment."),
                                fieldWithPath("content.[].subComments.[].text").description("Text of the sub comment."),
                                fieldWithPath("content.[].subComments.[].date").description("Date of the sub comment."),
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
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/CommentBook_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/CommentBook_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByCommentId() throws Exception {

        final String responseBodyContent = "{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[],\"_links\":{\"self\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"add\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true},\"getByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"getPageOfCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByCommentId\":{\"href\":\"http://localhost:8080/books/comments/1\"},\"deleteAllCommentsByBookId\":{\"href\":\"http://localhost:8080/books/{bookId}/comments\",\"templated\":true}}}";

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
                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(deleteByCommentId) the comment resource by id."),
                                linkWithRel("add").description("Link to add the comment resource."),
                                linkWithRel("getByCommentId").description("Link to get comment resource by comment id."),
                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource."),
                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource."),
                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id.")
                        )
                ));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"Comment with this commentId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/books/comments/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/books/comments/{commentId}", 1))
                .andExpect(status().isNotFound())
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

        final String responseBodyContent = "[{\"commentId\":1,\"userId\":1,\"text\":\"Text Comment One\",\"date\":\"01.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/1\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"commentId\":2,\"userId\":1,\"text\":\"Text Comment Two\",\"date\":\"02.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/2\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"commentId\":3,\"userId\":1,\"text\":\"Text Comment Three\",\"date\":\"03.11.2019\",\"subComments\":[],\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/books/1/comments\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedCommentsByBookId\",\"href\":\"http://localhost:8080/books/1/comments/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByCommentId\",\"href\":\"http://localhost:8080/books/comments/3\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]}]";

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
                .andExpect(status().isNotFound())
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
