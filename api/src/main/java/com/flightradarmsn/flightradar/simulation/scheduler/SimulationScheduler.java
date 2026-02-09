package com.flightradarmsn.flightradar.simulation.scheduler;

import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;
import com.flightradarmsn.flightradar.repository.FlightPlanRepository;
import com.flightradarmsn.flightradar.simulation.cache.FlightStateMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.service.FlightSimulationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

// Classe responsável por atualizar as posições a cada período
@Component
public class SimulationScheduler {

    @Autowired
    private FlightPlanRepository flightPlanRepository;

    @Autowired
    private FlightStateMemoryDatabase flightStateMemoryDatabase;

    @Autowired
    private FlightSimulationService flightSimulationService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    * A funcao e executada de acordo com a propriedade definida
    * em aplication.properties
    * */
    @Scheduled(fixedRateString = "${simulation.scheduler.fixed-rate-ms}")
    public void runSimulationCycle() {
        flightSimulationService.updateAllFlightsPositions();
    }

    /*
     * A funcao e executada de acordo com a propriedade definida
     * em aplication.properties
     *
     * Esta funcao e responsavel por atualizar o banco de dados real com o
     * banco de dados em memoria de acordo com cada tempo definido nas propriedades
     * */
    @Scheduled(fixedRateString = "${simulation.scheduler.update-database-rate-ms}")
    public void updateDatabase() {
        flightSimulationService.updateDatabase();
        logger.info("Banco de dados real atualizado com sucesso");
    }

    /*
     * A funcao e executada de acordo com a propriedade definida
     * em aplication.properties
     *
     * Esta funcao e responsavel por verificar se ha novos voos
     * inseridos no banco de dados que ainda nao foram adicionados
     * a simulacao
     * */
//    @Scheduled(fixedRateString = "5000")
    @Scheduled(fixedRateString = "${simulation.scheduler.start-pending-flights-rate-ms}")
    @Transactional
    public void startPendingFlights() {

        List<FlightPlan> pending = flightPlanRepository.findByFlightPhase(FlightPhase.SCHEDULED);

        for (FlightPlan plan : pending) {

            if (flightStateMemoryDatabase.flightStateExists(plan.getId())) continue;

            logger.info("Novo voo adicionado");

            flightSimulationService.start(plan);
        }
    }

}
