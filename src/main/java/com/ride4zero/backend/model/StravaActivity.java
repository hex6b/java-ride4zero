package com.ride4zero.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StravaActivity {
    @JsonProperty("resource_state")
    public Integer resourceState;
    public Athlete athlete;
    public String name;
    public Float distance;

    @JsonProperty("moving_time")
    public Integer movingTime;

    @JsonProperty("elapsed_time")
    public Integer elapsedTime;

    @JsonProperty("total_elevation_gain")
    public Float totalElevationGain;

    public String type;

    @JsonProperty("workout_type")
    public Integer workoutType;
}
