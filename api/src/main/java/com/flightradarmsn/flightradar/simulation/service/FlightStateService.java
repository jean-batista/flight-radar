package com.flightradarmsn.flightradar.simulation.service;

import com.flightradarmsn.flightradar.simulation.cache.FlightStateMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.exceptions.SimulationResourceNotFoundException;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Classe responsavel manipular os estados dos voos
 * */

@Service
public class FlightStateService {

    @Autowired
    private FlightStateMemoryDatabase flightStateMemoryDatabase;

    // Busca um estado pelo id do plano de voo
    public FlightState findFlightStateByFlightPlanId(Long id) {
        return flightStateMemoryDatabase.findFlightStateById(id).orElseThrow(
                () -> new SimulationResourceNotFoundException("Não foi possível encontrar um State com o id " + id)
        );
    }

    // Busca a listagem de estados de voos
    public List<FlightState> findAllFlightStates() {
        return flightStateMemoryDatabase.findAll();
    }


}
