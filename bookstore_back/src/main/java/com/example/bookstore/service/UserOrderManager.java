package com.example.bookstore.service;

import com.example.bookstore.entity.UserOrder;
import com.example.bookstore.repository.UserOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserOrderManager {
    private final UserOrderRepository userOrderRepository;

    public List<UserOrder> findAll() {
        return userOrderRepository.findAll();
    }

    public Optional<UserOrder> findById(Long id) {
        return userOrderRepository.findById(id);
    }

    public UserOrder save(UserOrder userOrder) {
        return userOrderRepository.save(userOrder);
    }

    public void delete(UserOrder userOrder) {
        userOrderRepository.delete(userOrder);
    }

    public void deleteById(Long id) {
        userOrderRepository.deleteById(id);
    }
}
