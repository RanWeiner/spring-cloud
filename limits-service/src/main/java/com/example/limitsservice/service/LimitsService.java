package com.example.limitsservice.service;

import com.example.limitsservice.configuration.LimitsConfig;
import com.example.limitsservice.model.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private final LimitsConfig config;

    public Limits getLimits(){
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
