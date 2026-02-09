package com.flightradarmsn.flightradar.simulation.cache;

import com.flightradarmsn.flightradar.simulation.exceptions.SimulationResourceNotFoundException;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Classe responsável por armazenar em memória o estado dos voos
@Service
public class FlightStateMemoryDatabase {

    private final Map<Long, FlightState> activeFlights;

    public FlightStateMemoryDatabase() {
        this.activeFlights = new ConcurrentHashMap<>();
    }

    public FlightStateMemoryDatabase(FlightState state) {
        if(state == null) throw new IllegalArgumentException("O State não pode ser nulo");
        activeFlights = new ConcurrentHashMap<>();
        activeFlights.put(state.getFlightPlanId(), state);
    }

    public FlightState save(FlightState state) {
        if(state == null) throw new IllegalArgumentException("O State não pode ser nulo");
        return this.activeFlights.put(state.getFlightPlanId(), state);
    }

    public Optional<FlightState> findFlightStateById(Long id) {
        return Optional.ofNullable(activeFlights.get(id));
    }

    public List<FlightState> findAll() {
        return activeFlights.values().stream().toList();
    }

    public FlightState update(FlightState state) {
        if(state == null) throw new IllegalArgumentException("O State não pode ser nulo");
        FlightState entity = findFlightStateById(state.getFlightPlanId()).orElseThrow(
                () -> new SimulationResourceNotFoundException("Não foi possível encontrar um State com o id: " + state.getFlightPlanId())
        );
        if(!entity.equals(state)) throw new RuntimeException("Object not equals!");
        return activeFlights.put(entity.getFlightPlanId(), state);
    }

    public void delete(FlightState state) {
        if(state == null) throw new IllegalArgumentException("O State não pode ser nulo");
        if(!flightStateExists(state.getFlightPlanId())) {
            throw new SimulationResourceNotFoundException("Não foi possível encontrar um State com o id: " + state.getFlightPlanId());
        }
        this.activeFlights.remove(state.getFlightPlanId());
    }

    public boolean flightStateExists(Long id) {
        return activeFlights.containsKey(id);
    }

}
