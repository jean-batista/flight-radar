package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.PersonDTO;
import com.flightradarmsn.flightradar.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public PersonDTO save(@RequestBody PersonDTO person) {
        return service.save(person);
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
