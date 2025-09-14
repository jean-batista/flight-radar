package com.flightradarmsn.flightradar.config;

import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.service.FlightPlanService;
import com.flightradarmsn.flightradar.service.FlightSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationConfig implements CommandLineRunner {

    @Autowired
    private FlightPlanService flightPlanService;

    @Autowired
    private FlightSimulationService simulationService;

    @Override
    public void run(String... args) throws Exception {
        var list = flightPlanService.findAll();
        for(FlightPlanDTO flightPlanDTO : list) {
            simulationService.startOrUpdateSimulation(flightPlanDTO);
        }
    }
}
