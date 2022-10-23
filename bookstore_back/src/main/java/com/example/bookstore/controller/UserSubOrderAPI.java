package com.example.bookstore.controller;

import com.example.bookstore.entity.UserSubOrder;
import com.example.bookstore.service.UserSubOrderManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/userSubOrders")
public class UserSubOrderAPI {
    private final UserSubOrderManager userSubOrderManager;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/all")
    public List<UserSubOrder> getAll() {
        return userSubOrderManager.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/id/{userSubOrderId}")
    public Optional<UserSubOrder> getById(@PathVariable("userSubOrderId") Long id) {
        return userSubOrderManager.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public UserSubOrder save(@RequestBody UserSubOrder userSubOrder) {
        return userSubOrderManager.save(userSubOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/upd")
    public UserSubOrder update(@RequestBody UserSubOrder userSubOrder) {
        return userSubOrderManager.save(userSubOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/param")
    public void delete(@RequestBody UserSubOrder userSubOrder) {
        userSubOrderManager.delete(userSubOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/{userSubOrderId}")
    public void deleteById(@PathVariable("userSubOrderId") Long id) {
        userSubOrderManager.deleteById(id);
    }
}
