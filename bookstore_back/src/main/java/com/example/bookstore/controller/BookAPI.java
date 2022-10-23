package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins =  "http://localhost:3000")
@RequestMapping("/api/books")
public class BookAPI {
    private final BookManager bookManager;

    @GetMapping("/get/all")
    public List<Book> getAll(){
        return bookManager.findAll();
    }

    @GetMapping(value = "/get/id/{bookId}")
    public Optional<Book> getById(@PathVariable("bookId") Long bookId){
        return bookManager.findById(bookId);
    }

    @GetMapping(value = "/get/category/{categoryId}")
    public List<Book> getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return bookManager.findByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public List<Book> searchForBook(@Param("name") String name) {
        return bookManager.searchForBook(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Book add(@RequestBody BookDTO book){
        return bookManager.saveDTO(book);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/upd")
    public Book update(@RequestBody Book book) {
        return bookManager.save(book);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/del/param")
    public void delete(@RequestBody Book book) {
        bookManager.delete(book);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/del/{bookId}")
    public void deleteById(@PathVariable("bookId") Long bookId) {
        bookManager.deleteById(bookId);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Book> getProductsWithPagination(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize){
        return bookManager.findProductsWithPagination(offset,pageSize);
    }

    @GetMapping(value = "/get/photo/{fileName}")
    public byte[] getImage(@PathVariable("fileName") String filename) {
        try {
            File imgPath = new File("./src/main/resources/images/" + filename);
            BufferedImage bImage = ImageIO.read(imgPath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



