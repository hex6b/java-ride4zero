package com.ride4zero.backend;

import com.ride4zero.backend.client.StravaApi;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.TotalDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class BackendApplication {

	@Autowired
	StravaApi stravaApi;

	public TotalDto getTotals() {
		return new TotalDto(1, 2, 3);
	}

	public JourneyDto getJourneys() {
		return new JourneyDto(10, 20, 30, 40, new Date(), "UK");
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
