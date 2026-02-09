package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightPlanRepository extends JpaRepository<FlightPlan, Long> {
    List<FlightPlan> findByFlightPhase(FlightPhase flightPhase);
}
