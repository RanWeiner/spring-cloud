package com.example.currencyexchangeservice.service;

import com.example.currencyexchangeservice.dal.CurrencyExchangeRepository;
import com.example.currencyexchangeservice.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeRepository repository;

    public CurrencyExchange findByFromAndTo(String from, String to) {
        return repository.findByFromAndTo(from,to);
    }
}
