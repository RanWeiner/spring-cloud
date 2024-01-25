package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.model.CurrencyExchange;
import com.example.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;
    private final Environment environment;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }


    @GetMapping("/exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to) {

        log.info("retrieveExchangeValue called with {} to {}", from, to);

        CurrencyExchange currencyExchange = currencyExchangeService.findByFromAndTo(from, to);

        if(currencyExchange ==null) {
            throw new RuntimeException("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
