package com.flightradarmsn.flightradar.config;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.repository.FlightPlanRepository;
import com.flightradarmsn.flightradar.simulation.service.FlightSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationConfig implements CommandLineRunner {

    @Autowired
    private FlightPlanRepository flightPlanRepository;

    @Autowired
    private FlightSimulationService simulationService;

    @Override
    public void run(String... args) throws Exception {
        var list = ObjectMapper.parseListObjects(flightPlanRepository.findAll(), FlightPlanDTO.class);
        for(FlightPlanDTO flightPlanDTO : list) {
            simulationService.start(flightPlanDTO);
        }
    }
}
