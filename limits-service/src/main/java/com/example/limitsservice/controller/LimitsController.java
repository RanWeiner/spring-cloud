package com.example.limitsservice.controller;

import com.example.limitsservice.model.Limits;
import com.example.limitsservice.service.LimitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {
    private final LimitsService limitsService;

    @GetMapping("/limits")
    public Limits getLimits(){
        return limitsService.getLimits();
    }
}
