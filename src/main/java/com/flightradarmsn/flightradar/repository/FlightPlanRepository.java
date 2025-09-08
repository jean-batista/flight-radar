package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightPlanRepository extends JpaRepository<FlightPlan, Long> {
}
