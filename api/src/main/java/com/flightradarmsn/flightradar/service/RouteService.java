package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.RouteDTO;
import com.flightradarmsn.flightradar.model.entities.Route;
import com.flightradarmsn.flightradar.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository repository;

    public RouteDTO save(RouteDTO route) {
        Route entity = ObjectMapper.parseObject(route, Route.class);
        return ObjectMapper.parseObject( repository.save(entity), RouteDTO.class);
    }

    public RouteDTO findById(Long id) {
        Route entity = repository.findById(id).orElseThrow();
        return ObjectMapper.parseObject(entity, RouteDTO.class);
    }

    public List<RouteDTO> findAll() {
        return ObjectMapper.parseListObjects(repository.findAll(), RouteDTO.class);
    }

}
