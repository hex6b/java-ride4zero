package com.ride4zero.backend.controller;

import com.ride4zero.backend.BackendApplication;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.TotalDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    BackendApplication backendApplication;

    @Value("${strava.token}")
    private String token;

    @GetMapping("/")
    public TotalDto getHello() {
        return backendApplication.getTotals();
    }

    @GetMapping("/journeys")
    public JourneyDto getJourneys() {
        return backendApplication.getJourneys();
    }
}
