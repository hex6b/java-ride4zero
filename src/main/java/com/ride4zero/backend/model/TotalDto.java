package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalDto {
    @JsonProperty("ride_count")
    Integer rideCount;

    @JsonProperty("distance_m")
    Integer distance;

    @JsonProperty("carbon_g")
    Integer carbon;
}
