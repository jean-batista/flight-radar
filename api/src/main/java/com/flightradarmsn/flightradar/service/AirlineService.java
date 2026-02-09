package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.model.dto.request.AirlineDTO;
import com.flightradarmsn.flightradar.model.dto.response.AirlineResponseDTO;
import com.flightradarmsn.flightradar.model.entities.Airline;
import com.flightradarmsn.flightradar.repository.AirlineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository repository;

    public AirlineResponseDTO save(AirlineDTO airlineDTO) {
        if(airlineDTO == null) throw new IllegalArgumentException("Não é possivel salvar uma airline nula");
        Airline entity = parseObject(airlineDTO, Airline.class);
        return parseObject(repository.save(entity), AirlineResponseDTO.class);
    }

    public List<AirlineResponseDTO> findAll() {
        return parseListObjects(repository.findAll(), AirlineResponseDTO.class);
    }

    public AirlineResponseDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma airline com o id " + id)
        );
        return parseObject(entity, AirlineResponseDTO.class);
    }

    public AirlineResponseDTO update(AirlineResponseDTO airlineResponseDTO) {

        Airline entity = repository.findById(airlineResponseDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma airline com o id " + airlineResponseDTO.getId())
        );

        BeanUtils.copyProperties(airlineResponseDTO, entity, "id");

        return parseObject(repository.save(entity), AirlineResponseDTO.class);
    }

    public void delete(Long id) {
        Airline airline = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma airline com o id " + id)
        );
        repository.delete(airline);
    }

}
