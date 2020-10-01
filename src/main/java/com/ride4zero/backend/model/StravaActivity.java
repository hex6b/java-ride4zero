package com.ride4zero.backend.model;

import lombok.Data;

@Data
public class StravaActivity {
    public Integer resourceState;
    public Athlete athlete;
    public String name;
    public Float distance;
    public Integer movingTime;
    public Integer elapsedTime;
    public Float totalElevationGain;
    public String type;
    public Integer workoutType;
}
