package com.flightradarmsn.flightradar.simulation.cache;

import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.exceptions.ObjectNotEqualsException;
import com.flightradarmsn.flightradar.simulation.exceptions.SimulationResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Classe responsável por armazenar em memória os planos de voos atualizados
@Service
public class FlightPlanMemoryDatabase {

    private final Map<Long, FlightPlan> activeFlightPlans;

    public FlightPlanMemoryDatabase() {
        this.activeFlightPlans = new ConcurrentHashMap<>();
    }

    public FlightPlanMemoryDatabase(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");
        activeFlightPlans = new ConcurrentHashMap<>();
        activeFlightPlans.put(plan.getId(), plan);
    }

    public FlightPlan save(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");
        return this.activeFlightPlans.put(plan.getId(), plan);
    }

    public FlightPlan update(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");
        FlightPlan entity = findFlightPlanById(plan.getId()).orElseThrow(
                () -> new SimulationResourceNotFoundException("Não foi possível encontrar o plano de voo com o id: " + plan.getId())
        );
        if(!entity.equals(plan)) throw new ObjectNotEqualsException("Os objetos são diferentes");
        return activeFlightPlans.put(entity.getId(), plan);
    }


    public Optional<FlightPlan> findFlightPlanById(Long id) {
        return Optional.ofNullable(activeFlightPlans.get(id));
    }

    public List<FlightPlan> findAll() {
        return activeFlightPlans.values().stream().toList();
    }

    public void delete(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");
        if(!flightStateExists(plan.getId())) {
            throw new SimulationResourceNotFoundException("Não foi possível encontrar o plano de voo com o id: " + plan.getId());
        }
        this.activeFlightPlans.remove(plan.getId());
    }

    public boolean flightStateExists(Long id) {
        return activeFlightPlans.containsKey(id);
    }


}
