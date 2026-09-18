package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Controller
 * Provides basic application availability endpoint.
 */
@RestController
public class HomeController {

    /**
     * Home Endpoint
     */
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Application is running");
    }
}