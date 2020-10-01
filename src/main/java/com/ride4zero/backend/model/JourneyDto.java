package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JourneyDto {
    @JsonProperty("ride_count")
    Integer rideCount;

    @JsonProperty("duration_m")
    Integer duration;

    @JsonProperty("distance_m")
    Integer distance;

    @JsonProperty("carbon_g")
    Integer carbon;

    @JsonProperty("timestamp")
    Date date;

    @JsonProperty("country")
    String country;
}
