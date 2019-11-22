package com.trl.libraryservice.temporary;

public class Test {
    public static void main(String[] args) {
//        GenreBookEntity genreBookEntity = null;
//
//        try {
//            GenreBookConverter.mapEntityToDTO(genreBookEntity);
//        } catch (IllegalMethodParameterException e) {
//            e.printStackTrace();
//        }


//        BookResource b = new BookResource(null);
//        b.getById(1L);

//        BookServiceImpl b = new BookServiceImpl(null, null);
//        b.getById(null);


//        UserDTO user = WebClient.create()
//                .get()
//                .uri("http://localhost:8081/user/2")
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
//                        Mono.error(new UserByIdNotExistException("use by this id not exist")))
//                .bodyToMono(UserDTO.class)
//                .block();
//
//        System.out.println(user);

    }


}

//class ApiCaller {
//
//    private WebClient webClient;
//
//    ApiCaller(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    Mono<SimpleResponseDto> callApi() {
//        return webClient.put()
//                .uri("/api/resource")
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "customAuth")
//                .syncBody(new SimpleRequestDto())
//                .retrieve()
//                .bodyToMono(SimpleResponseDto.class);
//    }
//}

/*
class ApiCallerTest {

    private final MockWebServer mockWebServer = new MockWebServer();
    private final ApiCaller apiCaller = new ApiCaller(WebClient.create(mockWebServer.url("/").toString()));

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void call() throws InterruptedException {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody("{\"y\": \"value for y\", \"z\": 789}")
        );
        SimpleResponseDto response = apiCaller.callApi().block();
        assertThat(response, is(not(nullValue())));
        assertThat(response.getY(), is("value for y"));
        assertThat(response.getZ(), is(789));

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        //use method provided by MockWebServer to assert the request header
        recordedRequest.getHeader("Authorization").equals("customAuth");
        DocumentContext context = JsonPath.parse(recordedRequest.getBody().inputStream());
        //use JsonPath library to assert the request body
        assertThat(context, isJson(allOf(
                withJsonPath("$.a", is("value1")),
                withJsonPath("$.b", is(123))
        )));
    }
}

https://stackoverflow.com/questions/45301220/how-to-mock-spring-webflux-webclient

*/

/**/