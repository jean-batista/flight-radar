package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.AirlineDTO;
import com.flightradarmsn.flightradar.model.entities.Airline;
import com.flightradarmsn.flightradar.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository repository;

    public AirlineDTO save(AirlineDTO flightPlanDTO) {
        Airline entity = parseObject(flightPlanDTO, Airline.class);
        return parseObject(repository.save(entity), AirlineDTO.class);
    }

    public AirlineDTO findById(Long id) {
        var entity = repository.findById(id);
        return parseObject(entity, AirlineDTO.class);
    }

}
