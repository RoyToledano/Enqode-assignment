package com.enqode.part2.service;

import com.enqode.part2.dto.response.DetailedInformationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class DarkWebScannerService {
    private final IntelXService intelXService;

    /**
     * Run intelX's service 'search domain' method and return the result.
     * In case of an error, the method throws an exception.
     * @param domain The given domain.
     * @param limit The given number of records requested.
     * @return The detailed information of the search result.
     */
    public Mono<ResponseEntity<DetailedInformationResponse>> scanDomain(String domain, int limit) {
        return intelXService.searchDomain(domain, limit)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error while scanning domain {}", domain, error);
                    throw new RuntimeException(error.getMessage());
                });
    }
}
