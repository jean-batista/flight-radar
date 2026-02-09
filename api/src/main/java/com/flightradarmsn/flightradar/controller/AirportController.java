package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.request.AirportDTO;
import com.flightradarmsn.flightradar.model.dto.response.AirportResponseDTO;
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
    public AirportResponseDTO save(@RequestBody AirportDTO airportDTO) {
        return service.save(airportDTO);
    }

    @GetMapping("/{id}")
    public AirportResponseDTO findbyId(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<AirportResponseDTO> findAll() {
        return service.findAll();
    }

    @PutMapping
    public AirportResponseDTO update(@RequestBody AirportResponseDTO airportResponseDTO) {
        return service.update(airportResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
