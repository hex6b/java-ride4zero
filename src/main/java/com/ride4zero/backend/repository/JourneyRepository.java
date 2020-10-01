package com.ride4zero.backend.repository;

import com.ride4zero.backend.entity.Journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
}
