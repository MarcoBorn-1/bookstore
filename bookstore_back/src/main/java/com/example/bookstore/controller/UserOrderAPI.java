package com.example.bookstore.controller;

import com.example.bookstore.entity.UserOrder;
import com.example.bookstore.service.UserOrderManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/userOrders")
public class UserOrderAPI {
    private final UserOrderManager userOrderManager;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/all")
    public List<UserOrder> getAll() {
        return userOrderManager.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get/id/{userOrderId}")
    public Optional<UserOrder> getById(@PathVariable("userOrderId") Long id) {
        return userOrderManager.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public UserOrder save(@RequestBody UserOrder userOrder) {
        return userOrderManager.save(userOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/upd")
    public UserOrder update(@RequestBody UserOrder userOrder) {
        return userOrderManager.save(userOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/param")
    public void delete(@RequestBody UserOrder userOrder) {
        userOrderManager.delete(userOrder);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/{userOrderId}")
    public void deleteById(@PathVariable("userOrderId") Long id) {
        userOrderManager.deleteById(id);
    }
}
