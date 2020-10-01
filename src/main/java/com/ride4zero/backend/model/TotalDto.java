package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalDto {
    @JsonProperty("ride_count")
    Integer totalRideCount;

    @JsonProperty("distance_m")
    Integer totalDistance;

    @JsonProperty("carbon_g")
    Integer totalCarbon;
}
