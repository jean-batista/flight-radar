package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.simulation.service.FlightStateService;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/simulation/v1")
public class SimulationController {

    @Autowired
    private FlightStateService service;

    @GetMapping("/status/{id}")
    public FlightState findFlightStateByFlightPlanId(@PathVariable Long id) {
        return service.findFlightStateByFlightPlanId(id);
    }

    @GetMapping("/status")
    public List<FlightState> findAllFlightStates() {
        return service.findAllFlightStates();
    }


}
