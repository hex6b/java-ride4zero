package com.ride4zero.backend;

import com.ride4zero.backend.client.StravaApi;
import com.ride4zero.backend.entity.Journey;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.StravaActivity;
import com.ride4zero.backend.model.TotalDto;
import com.ride4zero.backend.repository.JourneyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.asList;

@EnableFeignClients
@SpringBootApplication
public class BackendApplication {

	@Autowired
	StravaApi stravaApi;

	@Value("${strava.token}")
	private String stravaToken;

	@Autowired
	JourneyRepository journeyRepository;

	public TotalDto getTotals() {
		return new TotalDto(1, 2, 3);
	}

	public List<JourneyDto> getJourneys() {
		return asList(new JourneyDto(10, new Date(), 20, 30, 40, "UK"));
	}

	private void fetchAndStoreStravaActivies() {
		List<StravaActivity> allActivities = stravaApi.getAllActivities("Bearer " + stravaToken);
		journeyRepository.saveAll(mapActivities(allActivities));
	}

	private List<Journey> mapActivities(List<StravaActivity> activities) {
		List<Journey> journeys = new ArrayList<>();
		for (StravaActivity a : activities) {
			int distance = normaliseDistance(a.getDistance());
			journeys.add(new Journey(
					UUID.randomUUID(),
					new Date(),
					distance,
					a.getElapsedTime(),
					calculateCarbon(distance),
					"UK"));
		}
		return journeys;
	}

	private int calculateCarbon(int distance) {
		return 0;
	}

	private int normaliseDistance(Float d) {
		return 0;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
