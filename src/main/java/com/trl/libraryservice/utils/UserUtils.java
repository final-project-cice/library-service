package com.trl.libraryservice.utils;

import com.trl.libraryservice.controller.dto.UserDTO;
import com.trl.libraryservice.exception.UserNotExistException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class UserUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);


    public static UserDTO checkExistsUserById(Long userId, WebClient webClient) {

        return webClient
                .get()
                .uri("http://usermicroservicerestapi-env.xhvfm86kpa.eu-west-3.elasticbeanstalk.com/users/" + userId)
                .accept(MediaType.valueOf(MediaTypes.HAL_JSON_UTF8_VALUE))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new UserNotExistException("User by this id = " + userId + " not exist.")))
                .bodyToMono(UserDTO.class)
                .block();
    }
}