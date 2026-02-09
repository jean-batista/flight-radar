package com.flightradarmsn.flightradar.simulation.service;

import com.flightradarmsn.flightradar.model.dto.response.FlightPlanResponseDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.exceptions.SimulationResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class FlightPlanSimulationService {

    @Autowired
    private FlightPlanMemoryDatabase database;

    public FlightPlanResponseDTO save(FlightPlan flightPlan) {
        if(flightPlan == null) throw new IllegalArgumentException("Não é possivel salvar um plano de voo nulo");
        return parseObject(database.save(flightPlan), FlightPlanResponseDTO.class);
    }

    public FlightPlanResponseDTO findById(Long id) {
        var entity = database.findFlightPlanById(id).orElseThrow(
                () -> new SimulationResourceNotFoundException("Não foi possível encontrar um plano de voo com o id: " + id)
        );
        return parseObject(entity, FlightPlanResponseDTO.class);
    }

    public List<FlightPlanResponseDTO> findAll() {
        return parseListObjects(database.findAll(), FlightPlanResponseDTO.class);
    }

}
