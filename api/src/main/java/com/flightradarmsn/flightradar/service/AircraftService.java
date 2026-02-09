package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.model.dto.request.AircraftDTO;
import com.flightradarmsn.flightradar.model.dto.response.AircraftResponseDTO;
import com.flightradarmsn.flightradar.model.entities.Aircraft;
import com.flightradarmsn.flightradar.repository.AircraftRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository repository;

    public AircraftResponseDTO save(AircraftDTO aircraftDTO) {
        if(aircraftDTO == null) throw new IllegalArgumentException("Não é possivel salvar uma aeronave nula");
        Aircraft entity = parseObject(aircraftDTO, Aircraft.class);
        return parseObject(repository.save(entity), AircraftResponseDTO.class);
    }

    public List<AircraftResponseDTO> findAll() {
        return parseListObjects(repository.findAll(), AircraftResponseDTO.class);
    }

    public AircraftResponseDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma aeronave com o id " + id)
        );
        return parseObject(entity, AircraftResponseDTO.class);
    }

    public AircraftResponseDTO update(AircraftResponseDTO aircraftResponseDTO) {
        Aircraft entity = repository.findById(aircraftResponseDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma aeronave com o id " + aircraftResponseDTO.getId())
        );
        BeanUtils.copyProperties(aircraftResponseDTO, entity, "id");
        return parseObject(repository.save(entity), AircraftResponseDTO.class);
    }

    public void delete(Long id) {
        Aircraft aircraft = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma aeronave com o id " + id)
        );
        repository.delete(aircraft);
    }

}
