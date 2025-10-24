package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.RouteDTO;
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
    public RouteDTO save(@RequestBody RouteDTO route) {
        return service.save(route);
    }

    @GetMapping("/{id}")
    public RouteDTO findbyId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<RouteDTO> findAll() {
        return service.findAll();
    }

}
