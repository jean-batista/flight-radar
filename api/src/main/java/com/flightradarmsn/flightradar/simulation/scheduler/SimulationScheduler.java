package com.flightradarmsn.flightradar.simulation.scheduler;

import com.flightradarmsn.flightradar.simulation.service.FlightSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Classe responsável por atualizar as posições a cada período
@Component
public class SimulationScheduler {

    @Autowired
    private FlightSimulationService service;

    // Executa a função a cada 3 segundos
    @Scheduled(fixedRateString = "${simulation.scheduler.fixed-rate-ms}")
    public void runSimulationCycle() {
        service.updateAllFlightsPositions();
//        service.updateAllFlightPlans();
    }

}
