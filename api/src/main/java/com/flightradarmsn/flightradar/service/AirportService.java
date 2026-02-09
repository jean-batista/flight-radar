package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.request.AirportDTO;
import com.flightradarmsn.flightradar.model.dto.response.AirportResponseDTO;
import com.flightradarmsn.flightradar.model.entities.Airport;
import com.flightradarmsn.flightradar.repository.AirportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public AirportResponseDTO save(AirportDTO airportDTO) {
        if(airportDTO == null) throw new IllegalArgumentException("Não é possivel salvar um aeroporto nulo");
        Airport entity = ObjectMapper.parseObject(airportDTO, Airport.class);
        return ObjectMapper.parseObject( repository.save(entity), AirportResponseDTO.class);
    }

    public List<AirportResponseDTO> findAll() {
        return ObjectMapper.parseListObjects(repository.findAll(), AirportResponseDTO.class);
    }

    public AirportResponseDTO findById(Long id) {
        Airport entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um aeroporto com o id " + id)
        );
        return ObjectMapper.parseObject(entity, AirportResponseDTO.class);
    }

    public AirportResponseDTO update(AirportResponseDTO airportResponseDTO) {
        Airport entity = repository.findById(airportResponseDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um aeroporto com o id " + airportResponseDTO.getId())
        );
        BeanUtils.copyProperties(airportResponseDTO, entity);
        return ObjectMapper.parseObject(repository.save(entity), AirportResponseDTO.class);
    }

    public void delete(Long id) {
        Airport airport = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um aeroporto com o id " + id)
        );
        repository.delete(airport);
    }


}
