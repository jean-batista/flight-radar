package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
