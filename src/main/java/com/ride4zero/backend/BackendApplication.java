package com.ride4zero.backend;

import com.ride4zero.backend.client.StravaApi;
import com.ride4zero.backend.entity.Journey;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.StravaActivity;
import com.ride4zero.backend.model.TotalDto;
import com.ride4zero.backend.repository.JourneyRepository;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("getTotals called");
		//fetchAndStoreStravaActivies();
		return new TotalDto(1, 2, 3);
	}

	public List<JourneyDto> getJourneys() {
		return mapFromSchema(fetchAndStoreStravaActivies());
	}

	private List<Journey> fetchAndStoreStravaActivies() {
		List<StravaActivity> allActivities = stravaApi.getAllActivities("Bearer "
				+ stravaToken);
		return journeyRepository.saveAll(mapToSchema(allActivities));
	}

	private List<Journey> mapToSchema(List<StravaActivity> activities) {
		System.out.println(activities.size() + " ENTRIES="+ArrayUtils.toString(activities));
		List<Journey> journeys = new ArrayList<>();
		for (StravaActivity a : activities) {
			if (a.getType().equals("Ride")) {
				System.out.println("SAVING RECORD="+a.getName());
				int d = normaliseDistance(a.getDistance());
				journeys.add(new Journey(
						generateId(a.getElapsedTime(), d, a.getName()),
						new Date(),
						d,
						a.getElapsedTime(),
						calculateCarbon(d),
						"UK",
						1)
						);
			}
		}
		return journeys;
	}

	private List<JourneyDto> mapFromSchema(List<Journey> fromStrava) {
		List<JourneyDto> journeys = new ArrayList<>();

		for (Journey j : fromStrava) {
//			journeys.add(new JourneyDto(
//					j.get
//			))
		}

		return journeys;
	}

	private String generateId(Integer elapsedTime, Integer distance, String name) {
		return elapsedTime + "-" + distance + name.hashCode();
	}

	private int calculateCarbon(int distance) {
		return new Random().nextInt(); //todo real calc here
	}

	private int normaliseDistance(Float d) {
		return Math.round(d) /1000;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
