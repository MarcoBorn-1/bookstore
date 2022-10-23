package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.service.BookManager;
import com.example.bookstore.service.CustomerManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins =  "http://localhost:3000")
@RequestMapping("/api/customers")
public class CustomerAPI {
    private final CustomerManager customerManager;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/all")
    public List<Customer> getAll(){
        return customerManager.findAll();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/param")
    public List<Customer> getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return customerManager.findByNameAndSurname(name, surname);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get/id/{customerId}")
    public Optional<Customer> getId(@PathVariable("customerId") Long customerId){
        return customerManager.findById(customerId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Customer add(@RequestBody Customer customer){
        return customerManager.save(customer);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/upd")
    public Customer update(@RequestBody Customer customer) {
        return customerManager.save(customer);
    }


//    @PreAuthorize("hasAuthority('ADMIN')")
//    @DeleteMapping("/del/param")
//    public void delete(@RequestBody Customer customer) {
//        customerManager.delete(customer);
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/del/{customerId}")
    public void deleteById(@PathVariable("customerId") Long customerId) {
        customerManager.deleteById(customerId);
    }
}
