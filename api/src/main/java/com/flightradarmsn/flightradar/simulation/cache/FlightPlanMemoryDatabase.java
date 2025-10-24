package com.flightradarmsn.flightradar.simulation.cache;

import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Classe responsável por armazenar em memória os planos de voos atualizados
@Service
public class FlightPlanMemoryDatabase {

    private final Map<Long, FlightPlan> activeFlightPlans;

    public FlightPlanMemoryDatabase() {
        this.activeFlightPlans = new ConcurrentHashMap<>();
    }

    public FlightPlanMemoryDatabase(FlightPlan plan) {
        activeFlightPlans = new ConcurrentHashMap<>();
        activeFlightPlans.put(plan.getId(), plan);
    }

    public FlightPlan save(FlightPlan plan) {
        return this.activeFlightPlans.put(plan.getId(), plan);
    }

    public FlightPlan update(FlightPlan plan) {
        FlightPlan entity = findFlightPlanById(plan.getId());
        if(!entity.equals(plan)) throw new RuntimeException("Object not equals!");
        return activeFlightPlans.put(entity.getId(), plan);
    }


    public FlightPlan findFlightPlanById(Long id) {
        return activeFlightPlans.get(id);
    }

    public List<FlightPlan> findAll() {
        return activeFlightPlans.values().stream().toList();
    }

    public void delete(FlightPlan plan) {
        this.activeFlightPlans.remove(plan.getId());
    }

    public boolean flightStateExists(Long id) {
        return activeFlightPlans.containsKey(id);
    }


}
