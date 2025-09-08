package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.AircraftDTO;
import com.flightradarmsn.flightradar.model.entities.Aircraft;
import com.flightradarmsn.flightradar.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository repository;

    public AircraftDTO save(AircraftDTO flightPlanDTO) {
        Aircraft entity = parseObject(flightPlanDTO, Aircraft.class);
        return parseObject(repository.save(entity), AircraftDTO.class);
    }

    public AircraftDTO findById(Long id) {
        var entity = repository.findById(id);
        return parseObject(entity, AircraftDTO.class);
    }

}
