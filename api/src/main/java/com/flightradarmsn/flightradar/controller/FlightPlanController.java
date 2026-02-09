package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.request.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.dto.response.FlightPlanResponseDTO;
import com.flightradarmsn.flightradar.model.dto.response.FlightPlanMinResponseDTO;
import com.flightradarmsn.flightradar.model.dto.request.SearchFlightDTO;
import com.flightradarmsn.flightradar.service.FlightPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights/v1")
public class FlightPlanController {

    @Autowired
    private FlightPlanService service;

    @PostMapping
    public FlightPlanResponseDTO save(@RequestBody FlightPlanDTO flightPlanDTO) {
        return service.save(flightPlanDTO);
    }

    @GetMapping("/{id}")
    public FlightPlanResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<FlightPlanResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/min")
    public List<FlightPlanMinResponseDTO> findAllMin() {
        return service.findAllMin();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<FlightPlanResponseDTO> searchFlights(@ModelAttribute SearchFlightDTO criteria) {
        return service.searchFlights(criteria);
    }

}
