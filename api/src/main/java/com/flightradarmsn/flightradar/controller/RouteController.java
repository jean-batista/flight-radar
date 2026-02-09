package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.request.RouteDTO;
import com.flightradarmsn.flightradar.model.dto.response.RouteResponseDTO;
import com.flightradarmsn.flightradar.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes/v1")
public class RouteController {

    @Autowired
    private RouteService service;

    @PostMapping
    public RouteResponseDTO save(@RequestBody RouteDTO routeDTO) {
        return service.save(routeDTO);
    }

    @GetMapping("/{id}")
    public RouteResponseDTO findbyId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<RouteResponseDTO> findAll() {
        return service.findAll();
    }

    @PutMapping
    public RouteResponseDTO update(@RequestBody RouteResponseDTO route) {
        return service.update(route);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
