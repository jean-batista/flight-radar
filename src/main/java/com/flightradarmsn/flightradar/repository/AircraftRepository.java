package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
