package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.AirlineDTO;
import com.flightradarmsn.flightradar.model.dto.AirportDTO;
import com.flightradarmsn.flightradar.model.entities.Airport;
import com.flightradarmsn.flightradar.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public AirportDTO save(AirportDTO airport) {
        Airport entity = ObjectMapper.parseObject(airport, Airport.class);
        return ObjectMapper.parseObject( repository.save(entity), AirportDTO.class);
    }

    public AirportDTO findById(Long id) {
        Airport entity = repository.findById(id).orElseThrow();
        return ObjectMapper.parseObject(entity, AirportDTO.class);
    }

    public List<AirportDTO> findAll() {
        return ObjectMapper.parseListObjects(repository.findAll(), AirportDTO.class);
    }

}
