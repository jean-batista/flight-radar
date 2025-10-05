package com.flightradarmsn.flightradar.simulation.cache;

import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Classe responsável por armazenar em memória o estado dos voos
@Service
public class FlightStateMemoryDatabase {

    private final Map<Long, FlightState> activeFlights;

    public FlightStateMemoryDatabase() {
        this.activeFlights = new ConcurrentHashMap<>();
    }

    public FlightStateMemoryDatabase(FlightState state) {
        activeFlights = new ConcurrentHashMap<>();
        activeFlights.put(state.getFlightPlanId(), state);
    }

    public FlightState save(FlightState state) {
        return this.activeFlights.put(state.getFlightPlanId(), state);
    }

    public FlightState findFlightStateById(Long id) {
        return activeFlights.get(id);
    }

    public List<FlightState> findAll() {
        return activeFlights.values().stream().toList();
    }

    public FlightState update(FlightState state) {
        FlightState entity = findFlightStateById(state.getFlightPlanId());
        if(!entity.equals(state)) throw new RuntimeException("Object not equals!");
        return activeFlights.put(entity.getFlightPlanId(), state);
    }

    public void delete(FlightState state) {
        this.activeFlights.remove(state.getFlightPlanId());
    }

    public boolean flightStateExists(Long id) {
        return activeFlights.containsKey(id);
    }

}
