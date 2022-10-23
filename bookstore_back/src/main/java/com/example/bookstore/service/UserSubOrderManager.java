package com.example.bookstore.service;

import com.example.bookstore.entity.UserSubOrder;
import com.example.bookstore.repository.UserSubOrderRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSubOrderManager {
    private final UserSubOrderRepository userSubOrderRepository;

    public List<UserSubOrder> findAll() {
        return userSubOrderRepository.findAll();
    }

    public Optional<UserSubOrder> findById(Long id) {
        return userSubOrderRepository.findById(id);
    }

    public UserSubOrder save(UserSubOrder userSubOrder) {
        return userSubOrderRepository.save(userSubOrder);
    }

    public void delete(UserSubOrder userSubOrder) {
        userSubOrderRepository.delete(userSubOrder);
    }

    public void deleteById(Long id) {
        userSubOrderRepository.deleteById(id);
    }
}
