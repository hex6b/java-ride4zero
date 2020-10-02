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

	private boolean isDemo;

	StravaApi stravaApi;

	@Value("${strava.token}")
	private String stravaToken;

	JourneyRepository journeyRepository;

	TotalRepository totalRepository;

	public BackendApplication(StravaApi stravaApi, JourneyRepository journeyRepository, TotalRepository totalRepository) {
		this.isDemo = false;
		this.stravaApi = stravaApi;
		this.journeyRepository = journeyRepository;
		this.totalRepository = totalRepository;
	}

	public TotalDto getTotals() {
		log.info("getTotals called");
		return new TotalDto(totalRepository.findById(1).get());
	}

	public List<JourneyDto> getJourneys() {
		if (isDemo) {
//			return mapToDto();
		} else {
		}
		return mapToDto(fetchStravaActivies());
	}

	private List<JourneyDto> streamDemoData() {
		journeyRepository.findAll();
		return null;
	}

	private List<StravaActivity> fetchStravaActivies() {
		List<StravaActivity> allActivities = stravaApi.getAllActivities("Bearer "
				+ stravaToken);
		log.info(allActivities.size() + " ACTIVITIES="+ ArrayUtils.toString(allActivities));
		updateTotals(allActivities);
		journeyRepository.saveAll(mapToSchema(allActivities));
		return allActivities;
	}

	private void updateTotals(List<StravaActivity> allActivities) {
		Total totals = totalRepository.findById(1).get();

		int rideCount = totals.getTotalRideCount();
		int distance = totals.getTotalDistance();
		Float carbonCount = totals.getTotalCarbon();

		for (StravaActivity activity : allActivities) {
			if (!journeyRepository.existsById(generateId(activity.getElapsedTime(), normaliseDistance(activity.getDistance()), activity.getName()))) {
				rideCount += 1;
				distance += normaliseDistance(activity.getDistance());
				carbonCount += calculateCarbon(normaliseDistance(activity.getDistance()));
			}
		}

		totals.setTotalRideCount(rideCount);
		totals.setTotalDistance(distance);
		totals.setTotalCarbon(carbonCount);
		totalRepository.save(totals);
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

	private List<Journey> mapToSchema(List<StravaActivity> activities) {
		System.out.println(activities.size() + " ENTRIES="+ArrayUtils.toString(activities));
		List<Journey> journeys = new ArrayList<>();
		for (StravaActivity a : activities) {
			int distance = normaliseDistance(a.getDistance());
			journeys.add(new Journey(
					generateId(a.getElapsedTime(), distance, a.getName()),
					new Date(),
					distance,
					a.getElapsedTime(),
					calculateCarbon(distance),
					"UK", 1) );
			if (a.getType().equals("Ride")) {
				System.out.println("SAVING RECORD="+a.getName());
				int d = normaliseDistance(a.getDistance());
				journeys.add(new Journey(
						generateId(a.getElapsedTime(), d, a.getName()),
						new Date(),
						d,
						a.getElapsedTime(),
						calculateCarbon(d),
						"UK", 1)
				);
			}
		}
		return journeys;
	}

	private String generateId(Integer elapsedTime, Integer distance, String name) {
		return elapsedTime + "-" + distance + name.hashCode();
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

	public void play() {

	}
}
