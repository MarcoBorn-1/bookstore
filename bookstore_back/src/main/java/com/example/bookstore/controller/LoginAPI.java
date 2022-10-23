package com.example.bookstore.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bookstore.dto.LoginRequest;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.service.CustomerManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.Date;

import static com.example.bookstore.config.SecurityConstants.*;


@AllArgsConstructor
@RestController
@CrossOrigin(origins =  "http://localhost:3000")
@RequestMapping(path = LOGIN_URL)
public class LoginAPI {

    private final AuthenticationManager authenticationManager;
    private final CustomerManager customerManager;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            ).getPrincipal();


            final UserDetails customer = customerManager.loadUserByUsername(loginRequest.getEmail());
            String token = JWT.create()
                    .withSubject((customer.getUsername())).withClaim("role", customer.getAuthorities().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
            JSONObject resp = new JSONObject();
            resp.put("jwt-token", token);
            return new ResponseEntity<>(resp.toString(),HttpStatus.OK);
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}