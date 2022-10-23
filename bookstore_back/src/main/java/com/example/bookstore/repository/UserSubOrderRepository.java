package com.example.bookstore.repository;

import com.example.bookstore.entity.UserSubOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubOrderRepository extends JpaRepository<UserSubOrder, Long> {
}
