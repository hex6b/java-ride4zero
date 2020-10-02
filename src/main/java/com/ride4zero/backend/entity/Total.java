package com.ride4zero.backend.entity;

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
@Table(name = "totals")
@NoArgsConstructor
@AllArgsConstructor
public class Total {
    @Id
    Integer uid;

    @Column(name = "ride_count")
    Integer totalRideCount;

    @Column(name = "distance_m")
    Integer totalDistance;

    @Column(name = "carbon_g")
    Float totalCarbon;
}
