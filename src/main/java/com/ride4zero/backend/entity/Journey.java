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
    private String uid;

    @Column(name = "end_time")
    private Date timestamp;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "co2")
    private Float carbon;

    @Column(name = "country")
    private String country;

    @Column(name = "cycle_events")
    private Integer cycleEvents;
}
