package com.enqode.part2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .bodyToMono(String.class);
    }
}
