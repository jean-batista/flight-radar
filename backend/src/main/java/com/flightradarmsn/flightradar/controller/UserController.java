package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.ProfileDTO;
import com.flightradarmsn.flightradar.model.dto.TokenDTO;
import com.flightradarmsn.flightradar.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/v1")
public class UserController {

    @Autowired
    private AuthService service;

    @GetMapping
    public ProfileDTO getUserProfile() {
        return service.getUserProfile();
    }

    @PutMapping
    public TokenDTO updateUserProfile(@RequestBody ProfileDTO dto) {
        return service.updateUserProfile(dto);
    }

}
