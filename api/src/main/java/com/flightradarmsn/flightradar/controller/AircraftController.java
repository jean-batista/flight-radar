package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.request.AircraftDTO;
import com.flightradarmsn.flightradar.model.dto.response.AircraftResponseDTO;
import com.flightradarmsn.flightradar.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircrafts/v1")
public class AircraftController {

    @Autowired
    private AircraftService service;

    @PostMapping
    public AircraftResponseDTO save(@RequestBody AircraftDTO aircraftDTO) {
        return service.save(aircraftDTO);
    }

    @GetMapping
    public List<AircraftResponseDTO> findAll()  {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AircraftResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    public AircraftResponseDTO update(@RequestBody AircraftResponseDTO aircraftResponseDTO) {
        return service.update(aircraftResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
