package com.trl.libraryservice.utils;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.UserNotExistException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class UserUtils {

    public static UserDTO checkExistsUserById(Long userId, WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://user-service/user/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new UserNotExistException("User by this id = " + userId + " not exist.")))
                .bodyToMono(UserDTO.class)
                .block();
    }
}
