package com.example.bookstore.service;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerManager implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public ResponseEntity<String> signUpCustomer(Customer customer){
        boolean userExists = userRepository.findByEmail(customer.getEmail()).isPresent();
        if (userExists) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(customer.getPassword());

        customer.setPassword(encryptedPassword);

        userRepository.save(customer);

        return ResponseEntity.ok("User registered successfully!");
    }
        public List<Customer> findAll() {
        return userRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<Customer> findByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    public Customer save(Customer customer) {
        return userRepository.save(customer);
    }

    public void delete(Customer customer) {
        userRepository.delete(customer);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
