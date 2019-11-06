package com.trl.libraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class LibraryServiceApplication {

    @LoadBalanced
    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @LoadBalanced
    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }
}
