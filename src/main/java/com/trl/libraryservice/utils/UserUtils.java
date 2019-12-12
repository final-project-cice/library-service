package com.trl.libraryservice.utils;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.UserNotExistException;

import org.springframework.web.reactive.function.client.WebClient;

public class UserUtils {

    public static UserDTO checkExistsUserById(Long userId, WebClient.Builder webClientBuilder) {

        if (userId != 1) {
            throw new UserNotExistException("User by this id = " + userId + " not exist.");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        return userDTO;
        /*return webClientBuilder
                .build()
                .get()
                .uri("http://user-service/users/" + userId)
                .accept(MediaType.valueOf(MediaTypes.HAL_JSON_UTF8_VALUE))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new UserNotExistException("User by this id = " + userId + " not exist.")))
                .bodyToMono(UserDTO.class)
                .block();*/
    }
}
