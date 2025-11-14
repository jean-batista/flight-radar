package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.AccountCredentials;
import com.flightradarmsn.flightradar.model.dto.ProfileDTO;
import com.flightradarmsn.flightradar.model.dto.RegisterDTO;
import com.flightradarmsn.flightradar.model.dto.TokenDTO;
import com.flightradarmsn.flightradar.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO data) {
        service.register(data);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentials credentials) {
        if(credentials == null || credentials.getPassword().isBlank() || credentials.getUsername().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Request");
        }
        var token = service.signIn(credentials);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        if(username == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Request");
        }
        var token = service.refreshToken(username, refreshToken);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user")
    public ProfileDTO getUserProfile() {
        return service.getUserProfile();
    }

    @PutMapping("/user")
    public TokenDTO updateUserProfile(@RequestBody ProfileDTO dto) {
        return service.updateUserProfile(dto);
    }

}
