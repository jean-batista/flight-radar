package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.AirlineDTO;
import com.flightradarmsn.flightradar.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airlines/v1")
public class AirlineController {

    @Autowired
    private AirlineService service;

    @PostMapping
    public AirlineDTO save(@RequestBody AirlineDTO flightPlanDTO) {
        return service.save(flightPlanDTO);
    }

    @GetMapping
    public AirlineDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

}
