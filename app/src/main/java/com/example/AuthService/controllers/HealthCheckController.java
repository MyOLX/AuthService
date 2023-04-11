package com.example.AuthService.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @CrossOrigin
    @GetMapping("/health")
    public String HealthCheck() {
        return "Ping!";
    }
}
