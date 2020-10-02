package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JourneyDto {
    @JsonProperty("ride_count")
    private Integer rideCount;

    @JsonProperty("timestamp")
    private Date date;

    @JsonProperty("distance_m")
    private Integer distances;

    @JsonProperty("duration_m")
    private Integer durations;

    @JsonProperty("carbon_g")
    private Float carbon;

    @JsonProperty("country")
    private String country;
}
