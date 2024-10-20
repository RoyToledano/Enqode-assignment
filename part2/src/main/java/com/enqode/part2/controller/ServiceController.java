package com.enqode.part2.controller;

import com.enqode.part2.dto.response.DetailedInformationResponse;
import com.enqode.part2.service.DarkWebScannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/darkweb")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {
    private final DarkWebScannerService darkWebScannerService;

    @GetMapping("/scan")
    public Mono<ResponseEntity<DetailedInformationResponse>> scanSelector(@RequestParam String selector, @RequestParam int limit) {
        log.info("Scanning dark web data for selector: {}", selector);
        return darkWebScannerService.scanSelector(selector, limit);
    }
}
