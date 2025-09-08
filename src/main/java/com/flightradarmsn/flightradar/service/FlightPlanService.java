package com.flightradarmsn.flightradar.service;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.*;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.entities.Aircraft;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.repository.AircraftRepository;
import com.flightradarmsn.flightradar.repository.AirlineRepository;
import com.flightradarmsn.flightradar.repository.FlightPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightPlanService {

    @Autowired
    private FlightPlanRepository repository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineRepository airlineRepository;

//    public FlightPlanDTO save(FlightPlanDTO flightPlanDTO) {
//        FlightPlan entity = parseObject(flightPlanDTO, FlightPlan.class);
//        return parseObject(repository.save(entity), FlightPlanDTO.class);
//    }

    public FlightPlanDTO save(FlightPlanDTO flightPlanDTO) {
        FlightPlan entity = parseObject(flightPlanDTO, FlightPlan.class);
        airlineRepository.save(entity.getAirline());
        aircraftRepository.save(entity.getAircraft());
        repository.save(entity);
        return parseObject(entity, FlightPlanDTO.class);
    }

    public FlightPlanDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow();
        System.out.println(entity);
        return parseObject(entity, FlightPlanDTO.class);
    }

    public List<FlightPlanDTO> findAll() {
        return parseListObjects(repository.findAll(), FlightPlanDTO.class);
    }

}
