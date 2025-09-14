package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.AirportDTO;
import com.flightradarmsn.flightradar.model.entities.Airport;
import com.flightradarmsn.flightradar.repository.AirportRepository;
import com.flightradarmsn.flightradar.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports/v1")
public class AirportController {

    @Autowired
    private AirportService service;

    @PostMapping
    public AirportDTO save(@RequestBody AirportDTO airport) {
        return service.save(airport);
    }

    @GetMapping("/{id}")
    public AirportDTO findbyId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<AirportDTO> findAll() {
        return service.findAll();
    }

}
