package com.example.bookstore.controller;

import com.example.bookstore.dto.RegistrationRequest;
import com.example.bookstore.service.RegistrationManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.bookstore.config.SecurityConstants.SIGN_UP_URL;

@RestController
@CrossOrigin(origins =  "http://localhost:3000")
@RequestMapping(path = SIGN_UP_URL)
@AllArgsConstructor
public class RegistrationAPI {
    private RegistrationManager registrationManager;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request){
        return registrationManager.register(request);
    }
}
