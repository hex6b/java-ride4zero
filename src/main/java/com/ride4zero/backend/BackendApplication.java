package com.ride4zero.backend;

import com.ride4zero.backend.client.StravaApi;
import com.ride4zero.backend.entity.Journey;
import com.ride4zero.backend.entity.Total;
import com.ride4zero.backend.model.JourneyDto;
import com.ride4zero.backend.model.StravaActivity;
import com.ride4zero.backend.model.TotalDto;
import com.ride4zero.backend.repository.JourneyRepository;
import com.ride4zero.backend.repository.TotalRepository;

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

	@Autowired
	TotalRepository totalRepository;

	public TotalDto getTotals() {
		log.info("getTotals called");
		return new TotalDto(totalRepository.findById(1).get());
	}

	public List<JourneyDto> getJourneys() {
		return mapToDto(fetchStravaActivies());
	}

	private List<StravaActivity> fetchStravaActivies() {
		List<StravaActivity> allActivities = stravaApi.getAllActivities("Bearer "
				+ stravaToken);
		log.info(allActivities.size() + " ACTIVITIES="+ ArrayUtils.toString(allActivities));
		updateTotals(allActivities);
		return allActivities;
	}

	private void updateTotals(List<StravaActivity> allActivities) {
		Total totals = totalRepository.findById(1).get();

		int rideCount = totals.getTotalRideCount();
		int distance = totals.getTotalDistance();
		Float carbonCount = totals.getTotalCarbon();

		for (StravaActivity activity : allActivities) {
			rideCount += 1;
			distance += normaliseDistance(activity.getDistance());
			carbonCount += calculateCarbon(normaliseDistance(activity.getDistance()));
		}

		totals.setTotalRideCount(rideCount);
		totals.setTotalDistance(distance);
		totals.setTotalCarbon(carbonCount);
	}

	private List<JourneyDto> mapToDto(List<StravaActivity> activities) {
		List<JourneyDto> res = new ArrayList<>();

		int rideCount = 0;
		int distance = 0;
		int duration = 0;
		float carbon = 0.0F;

		for (StravaActivity activity : activities) {
			rideCount += 1;
			distance += normaliseDistance(activity.getDistance());
			duration += activity.getElapsedTime();
			carbon += calculateCarbon(normaliseDistance(activity.getDistance()));
		}
		res.add(new JourneyDto(rideCount, new Date(), distance, duration, carbon, "UK"));
		return res;
	}

	private int normaliseDistance(Float d) {
		return Math.round(d) /1000;
	}

	private Float calculateCarbon(int distance) {
		return new Random().nextFloat(); //todo real calc here
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
