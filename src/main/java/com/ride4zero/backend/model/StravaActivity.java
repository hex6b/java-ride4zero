package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StravaActivity {
    @JsonProperty("resource_state")
    private Integer resourceState;
    private Athlete athlete;
    private String name;
    private Float distance;

    @JsonProperty("moving_time")
    private Integer movingTime;

    @JsonProperty("elapsed_time")
    private Integer elapsedTime;

    @JsonProperty("total_elevation_gain")
    private Float totalElevationGain;

    private String type;

    @JsonProperty("workout_type")
    private Integer workoutType;
}
