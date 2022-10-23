package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorByNameAndSurname(String name, String surname);

    List<Author> findAuthorByNameContainingIgnoreCase(String name);

    List<Author> findAuthorBySurnameContainingIgnoreCase(String name);
}
