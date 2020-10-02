package com.ride4zero.backend.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "journeys")
@NoArgsConstructor
@AllArgsConstructor
public class Journey {
    @Id
    String uid;

    @Column(name = "end_time")
    Date date;

    @Column(name = "distance")
    Integer distance;

    @Column(name = "duration")
    Integer duration;

    @Column(name = "co2")
    Integer carbon;

    @Column(name = "country")
    String country;

    @Column(name = "cycle_events")
    Integer cycleEvents;
}
