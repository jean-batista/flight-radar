package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.AircraftDTO;
import com.flightradarmsn.flightradar.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aircrafts/v1")
public class AircraftController {

    @Autowired
    private AircraftService service;

    @PostMapping
    public AircraftDTO save(@RequestBody AircraftDTO flightPlanDTO) {
        return service.save(flightPlanDTO);
    }

    @GetMapping
    public AircraftDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

}
