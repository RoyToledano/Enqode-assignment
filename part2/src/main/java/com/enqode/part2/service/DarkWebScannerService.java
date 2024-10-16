package com.enqode.part2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DarkWebScannerService {
    private IntelXService intelXService;

    public Mono<ResponseEntity<String>> scanDomain(String domain) {
        return intelXService.searchDomain(domain)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
