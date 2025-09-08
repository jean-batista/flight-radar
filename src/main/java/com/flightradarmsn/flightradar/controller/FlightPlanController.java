package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.service.FlightPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;

@RestController
@RequestMapping("/api/flights/v1")
public class FlightPlanController {

    @Autowired
    private FlightPlanService service;

    @PostMapping
    public FlightPlanDTO save(@RequestBody FlightPlanDTO flightPlanDTO) {
        return service.save(flightPlanDTO);
    }

    @GetMapping("/{id}")
    public FlightPlanDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<FlightPlanDTO> findAll() {
        return service.findAll();
    }

}
