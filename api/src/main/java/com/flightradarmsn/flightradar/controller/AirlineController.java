package com.flightradarmsn.flightradar.controller;


import com.flightradarmsn.flightradar.model.dto.request.AirlineDTO;
import com.flightradarmsn.flightradar.model.dto.response.AirlineResponseDTO;
import com.flightradarmsn.flightradar.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines/v1")
public class AirlineController {

    @Autowired
    private AirlineService service;

    @PostMapping
    public AirlineResponseDTO save(@RequestBody AirlineDTO airlineDTO) {
        return service.save(airlineDTO);
    }

    @GetMapping
    public List<AirlineResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AirlineResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    public AirlineResponseDTO update(@RequestBody AirlineResponseDTO airlineResponseDTO) {
        return service.update(airlineResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
