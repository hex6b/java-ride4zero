package com.ride4zero.backend.controller;

import com.ride4zero.backend.BackendApplication;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.TotalDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    BackendApplication stravaService;

    @GetMapping("/totals")
    public TotalDto getHello() {
        return stravaService.getTotals();
    }

    @GetMapping("/journeys")
    public List<JourneyDto> getJourneys() {
        return stravaService.getJourneys();
    }

    @PostMapping("/play")
    public void play() {
        stravaService.play();
    }
}
