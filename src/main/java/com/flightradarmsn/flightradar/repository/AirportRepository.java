package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
