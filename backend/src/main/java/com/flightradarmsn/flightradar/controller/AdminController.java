package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.DeleteProfileDTO;
import com.flightradarmsn.flightradar.model.dto.ProfileMinDTO;
import com.flightradarmsn.flightradar.model.dto.UserDTO;
import com.flightradarmsn.flightradar.service.AdminService;
import com.flightradarmsn.flightradar.service.UserService;
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

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @PutMapping
    public void updateUserProfile(@RequestBody ProfileMinDTO profileDTO) {
        service.updateUserProfile(profileDTO);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody DeleteProfileDTO profileDTO) {
        service.deleteUser(profileDTO);
    }

}
