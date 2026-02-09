package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.DeleteProfileDTO;
import com.flightradarmsn.flightradar.model.dto.ProfileMinDTO;
import com.flightradarmsn.flightradar.model.dto.UserDTO;
import com.flightradarmsn.flightradar.service.AdminService;
import com.flightradarmsn.flightradar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1")
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @PutMapping
    public void updateUserProfile(@RequestBody @Valid ProfileMinDTO profileDTO) {
        service.updateUserProfile(profileDTO);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteProfileDTO profileDTO) {
        service.deleteUser(profileDTO);
    }

}
