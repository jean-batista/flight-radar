package com.flightradarmsn.flightradar.simulation.service;

import com.flightradarmsn.flightradar.simulation.cache.FlightStateMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightStateService {

    @Autowired
    private FlightStateMemoryDatabase flightStateMemoryDatabase;

    public FlightState findFlightStateByFlightPlanId(Long id) {
        return flightStateMemoryDatabase.findFlightStateById(id);
    }

    public List<FlightState> findAllFlightStates() {
        return flightStateMemoryDatabase.findAll();
    }


}
