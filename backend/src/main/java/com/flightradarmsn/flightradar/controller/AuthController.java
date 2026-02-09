package com.flightradarmsn.flightradar.controller;

import com.flightradarmsn.flightradar.model.dto.AccountCredentials;
import com.flightradarmsn.flightradar.model.dto.RegisterDTO;
import com.flightradarmsn.flightradar.service.AuthService;
import jakarta.validation.Valid;
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
    public void register(@RequestBody @Valid RegisterDTO data) {
        service.register(data);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid AccountCredentials credentials) {
        if(credentials == null || credentials.getPassword().isBlank() || credentials.getUsername().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Request");
        }
        var token = service.signIn(credentials);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(@PathVariable("username") @Valid String username, @RequestHeader("Authorization") @Valid String refreshToken) {
        if(username == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Request");
        }
        var token = service.refreshToken(username, refreshToken);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok(token);
    }

}
