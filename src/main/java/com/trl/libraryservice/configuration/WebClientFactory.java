package com.trl.libraryservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientFactory {

    public WebClientFactory() {
    }

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
}
