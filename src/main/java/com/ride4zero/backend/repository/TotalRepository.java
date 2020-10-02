package com.ride4zero.backend.repository;

import com.ride4zero.backend.entity.Total;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends JpaRepository<Total, Integer> {
}
