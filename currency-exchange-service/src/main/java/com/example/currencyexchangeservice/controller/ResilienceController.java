package com.example.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ResilienceController {

    private final RestTemplate restTemplate;

    @GetMapping("/retry")
    @Retry(name = "default", fallbackMethod = "hardcodedResponse")
    public String test1() {
        //will do retries as per configuration (default 3) and then return response or execute fallback method
        log.info("Sample api call received");
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/some-dummy-url", String.class);
		return forEntity.getBody();
    }

    @GetMapping("/circuitbreaker")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String test2() {
        // will open circuit breaker based on configuration
        log.info("Sample api call received");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
    }

    @GetMapping("/ratelimiter")
    @RateLimiter(name = "default")
    public String test3() {
        // permit only configured requests per time duration
        log.info("Sample api call received");
        return "sample-api";
    }

    @GetMapping("/bulkhead")
    @Bulkhead(name = "default")
    public String test4() {
        // permit only configured number of  concurrent calls
        log.info("Sample api call received");
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
