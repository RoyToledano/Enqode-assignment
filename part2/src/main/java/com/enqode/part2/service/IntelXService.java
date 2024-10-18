package com.enqode.part2.service;

import com.enqode.part2.dto.response.IntelligentSearchRequestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class IntelXService {
    private final WebClient webClient;

    @Value("${intelx.api.key}")
    private String apiKey;

    public IntelXService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://2.intelx.io").build();
    }

    public Mono<String> searchDomain(String domain) {
        return sendSearchRequest(domain)
                .flatMap(response -> {
                    // Use the ID from the first response to make the second request
                    String id = response.id();
                    Mono<String> detailedInformation = getDetailedInformation(id);
                    sendTerminateSearchRequest(id);
                    return detailedInformation;
                });
    }

    private Mono<IntelligentSearchRequestResponse> sendSearchRequest(String domain) {
        String requestBody = String.format("""
                {
                    "term": "%s",
                    "buckets": [],
                    "lookuplevel": 0,
                    "maxresults": 1000,
                    "timeout": 0,
                    "datefrom": "",
                    "dateto": "",
                    "sort": 2,
                    "media": 0,
                    "terminate": []
                }
            """, domain);

        return webClient.post()
                .uri("/intelligent/search")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-key", apiKey)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(IntelligentSearchRequestResponse.class);
    }

    private Mono<String> getDetailedInformation(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/intelligent/search/result")
                        .queryParam("id", id)
                        .queryParam("limit", 10)
                        .build())
                .header("x-key", apiKey)
                .retrieve()
                .bodyToMono(String.class);
    }

    private void sendTerminateSearchRequest(String id) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/intelligent/search/terminate")
                        .queryParam("id", id)
                        .build())
                .header("x-key", apiKey)
                .retrieve();
    }
}
