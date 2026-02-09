package com.flightradarmsn.flightradar.simulation.controller;

import com.flightradarmsn.flightradar.model.dto.response.FlightPlanResponseDTO;
import com.flightradarmsn.flightradar.simulation.service.FlightPlanSimulationService;
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
    private FlightStateService flightStateService;

    @Autowired
    private FlightPlanSimulationService flightPlanSimulationService;

    @GetMapping("/status/{id}")
    public FlightState findFlightStateByFlightPlanId(@PathVariable Long id) {
        return flightStateService.findFlightStateByFlightPlanId(id);
    }

    @GetMapping("/status")
    public List<FlightState> findAllFlightStates() {
        return flightStateService.findAllFlightStates();
    }

    private FlightPlanResponseDTO findById(@PathVariable Long id) {
        return flightPlanSimulationService.findById(id);
    }

    private List<FlightPlanResponseDTO> findAll() {
        return flightPlanSimulationService.findAll();
    }

}
