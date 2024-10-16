package com.enqode.part2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DarkWebScannerService {
    private IntelXService intelXService;

    public Mono<ResponseEntity<String>> scanDomain(String domain) {
        return intelXService.searchDomain(domain)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    log.error("Error while scanning domain {}", domain, error);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("An error occurred: " + error.getMessage()));
                });
    }
}
