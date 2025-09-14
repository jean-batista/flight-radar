package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.service.FlightSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulations/v1")
public class FlightSimulationController {

    @Autowired
    private FlightSimulationService service;

    @GetMapping("/start")
    public ResponseEntity<String> startFlightSimulation(@RequestBody FlightPlanDTO plan) {
        if(plan == null || plan.getId() == null) {
            return ResponseEntity.badRequest().body("Objeto FlightPlan inválido ou sem ID.");
        }
        // Inicia o serviço de simulação
        service.startOrUpdateSimulation(plan);
        return ResponseEntity.ok("Simulação iniciada com o ID: " + plan.getId());
    }

    @GetMapping("/status")
    public ResponseEntity<List<FlightPlanDTO>> getFlightsStatus() {
        return ResponseEntity.ok(service.getFlightPlans());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<FlightPlanDTO> getFlightStatus(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFlightPlan(id));
    }

}
