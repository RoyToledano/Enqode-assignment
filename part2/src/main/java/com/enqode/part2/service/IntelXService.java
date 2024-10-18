package com.enqode.part2.service;

import com.enqode.part2.dto.response.DetailedInformationResponse;
import com.enqode.part2.dto.response.IntelligentSearchRequestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class IntelXService {
    private final WebClient webClient;

    @Value("${intelx.api.key}")
    private String apiKey;

    public IntelXService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://2.intelx.io").build();
    }

    /**
     * Send search request to IntelX API and then pull the results.
     * After successfully pull, the method sends request to terminate the search.
     * @param domain The given domain.
     * @param limit The given number of records requested.
     * @return The detailed information of the search result.
     */
    public Mono<DetailedInformationResponse> searchDomain(String domain, int limit) {
        return sendSearchRequest(domain)
                .flatMap(response -> {
                    log.info("Successfully search domain request sent {}", domain);
                    String id = response.id();
                    Mono<DetailedInformationResponse> detailedInformation = getDetailedInformation(id, limit);
                    log.info("Detailed information received");
                    sendTerminateSearchRequest(id);
                    log.info("Successfully terminate search request sent {}", id);
                    return detailedInformation;
                });
    }

    /**
     * Send search request to IntelX API and return the search request response.
     * @param domain The given domain.
     * @return The search request response.
     */
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

    /**
     * Send request to pull the search results from IntelX API.
     * @param id The search ID.
     * @param limit The given number of records requested.
     * @return The detailed information of the search result.
     */
    private Mono<DetailedInformationResponse> getDetailedInformation(String id, int limit) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/intelligent/search/result")
                        .queryParam("id", id)
                        .queryParam("limit", limit)
                        .build())
                .header("x-key", apiKey)
                .retrieve()
                .bodyToMono(DetailedInformationResponse.class);
    }

    /**
     * Send request to terminate the search.
     * @param id The search ID.
     */
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
