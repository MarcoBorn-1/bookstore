package com.example.bookstore.service;

import com.example.bookstore.dto.RegistrationRequest;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistrationManager {
    private final CustomerManager customerManager;
    private EmailValidator emailValidator;
    public ResponseEntity<String> register(RegistrationRequest request) {
        ArrayList<String> neededInfo = new ArrayList<>();
        if (request.getName() == null) neededInfo.add("Name");
        if (request.getSurname() == null) neededInfo.add("Surname");
        if (request.getEmail() == null) neededInfo.add("E-Mail");
        if (request.getPassword() == null) neededInfo.add("Password");
        if (request.getPhoneNumber() == null) neededInfo.add("Phone number");

        if (!neededInfo.isEmpty()) {
            String listString = neededInfo.stream().map(Object::toString)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return customerManager.signUpCustomer(new Customer(request.getName(), request.getSurname(),
                request.getPhoneNumber(), request.getEmail(), request.getPassword(), Role.USER));
    }
}
