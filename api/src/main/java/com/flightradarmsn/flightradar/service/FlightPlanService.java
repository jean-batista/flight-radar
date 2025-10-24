package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.dto.FlightPlanMinDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class FlightPlanService {

    @Autowired
    private FlightPlanMemoryDatabase database;

    public FlightPlanDTO save(FlightPlanDTO flightPlanDTO) {
        FlightPlan entity = parseObject(flightPlanDTO, FlightPlan.class);
        return parseObject(database.save(entity), FlightPlanDTO.class);
    }

    public FlightPlanDTO findById(Long id) {
        var entity = database.findFlightPlanById(id);
        return parseObject(entity, FlightPlanDTO.class);
    }

    public List<FlightPlanDTO> findAll() {
        return parseListObjects(database.findAll(), FlightPlanDTO.class);
    }

    public List<FlightPlanMinDTO> findAllMin() {
        return parseListObjects(database.findAll(), FlightPlanMinDTO.class);
    }


}
