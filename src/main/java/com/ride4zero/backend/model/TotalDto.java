package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ride4zero.backend.entity.Total;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalDto {

    public TotalDto(Total total) {
        this.totalRideCount = total.getTotalRideCount();
        this.totalDistance = total.getTotalDistance();
        this.totalCarbon = total.getTotalCarbon();
    }

    @JsonProperty("ride_count")
    Integer totalRideCount;

    @JsonProperty("distance_m")
    Integer totalDistance;

    @JsonProperty("carbon_g")
    Float totalCarbon;
}
