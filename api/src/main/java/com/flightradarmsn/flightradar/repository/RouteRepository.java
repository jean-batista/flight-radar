package com.flightradarmsn.flightradar.repository;

import com.flightradarmsn.flightradar.model.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
