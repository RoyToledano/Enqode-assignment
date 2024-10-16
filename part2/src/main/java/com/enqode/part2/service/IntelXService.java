package com.enqode.part2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatusCode;

@Service
public class IntelXService {
    private final WebClient webClient;

    @Value("${intelx.api.key}")
    private String apiKey;

    @Value("${intelx.api.url}")
    private String apiUrl;

    public IntelXService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> searchDomain(String domain) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiUrl + "/intelligent/search")
                        .queryParam("term", domain)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                //Handles client-side errors
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new RuntimeException("Client error occurred!"));
                })
                //Handles server-side (IntelX) errors
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return Mono.error(new RuntimeException("Server error occurred!"));
                })
                .bodyToMono(String.class)
                //Handle other errors
                .doOnError(WebClientResponseException.class, e -> {
                    System.err.println("Error response from IntelX: " + e.getMessage());
                })
                .onErrorResume(e -> {
                    return Mono.just("Error: Unable to fetch data from IntelX.");
                });
    }
}
