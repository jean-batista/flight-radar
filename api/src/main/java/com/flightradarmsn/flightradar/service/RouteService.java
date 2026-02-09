package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.request.RouteDTO;
import com.flightradarmsn.flightradar.model.dto.response.RouteResponseDTO;
import com.flightradarmsn.flightradar.model.entities.Route;
import com.flightradarmsn.flightradar.repository.RouteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class RouteService {

    @Autowired
    private RouteRepository repository;

    public RouteResponseDTO save(RouteDTO routeDTO) {
        if(routeDTO == null) throw new IllegalArgumentException("Não é possivel salvar uma rota nula");
        Route entity = ObjectMapper.parseObject(routeDTO, Route.class);
        return ObjectMapper.parseObject( repository.save(entity), RouteResponseDTO.class);
    }

    public RouteResponseDTO findById(Long id) {
        Route entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma rota com o id " + id)
        );
        return ObjectMapper.parseObject(entity, RouteResponseDTO.class);
    }

    public List<RouteResponseDTO> findAll() {
        return ObjectMapper.parseListObjects(repository.findAll(), RouteResponseDTO.class);
    }

    public RouteResponseDTO update(RouteResponseDTO routeResponseDTO) {
        Route entity = repository.findById(routeResponseDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma rota com o id " + routeResponseDTO.getId())
        );
        BeanUtils.copyProperties(routeResponseDTO, entity, "id");
        return parseObject(repository.save(entity), RouteResponseDTO.class);
    }

    public void delete(Long id) {
        Route entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma rota com o id " + id)
        );
        repository.delete(entity);
    }

}
