package com.example.currencyconversionservice.client;

import com.example.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeFeignClient {

    @GetMapping("ping")
    String ping();

    @GetMapping("/v1/exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);
}
