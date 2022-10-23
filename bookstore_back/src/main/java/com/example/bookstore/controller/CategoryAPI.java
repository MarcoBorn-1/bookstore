package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.service.CategoryManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryAPI {
    private final CategoryManager categoryManager;

    @GetMapping("/get/all")
    public Iterable<Category> getAll() {
        return categoryManager.findAll();
    }

    @GetMapping("/get/id/{categoryID}")
    public Optional<Category> getById(@PathVariable("categoryID") Long categoryId) {
        return categoryManager.findById(categoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Category add(@RequestBody Category category) {
        return categoryManager.save(category);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/upd")
    public Category update(@RequestBody Category category) {
        return categoryManager.save(category);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/param")
    public void delete(@RequestBody Category category) {
        categoryManager.delete(category);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/del/{categoryID}")
    public void deleteById(@PathVariable("categoryID") Long categoryId) {
        categoryManager.deleteById(categoryId);
    }
}
