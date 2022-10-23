package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

// Test

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int price;
    private int year;
    // max size = 255
    // bigger size - use @Lob
    private String description;
    private String photoURL;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "surname")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Book(String title, int price, int year, String description, Author author, Category category, String photoURL) {
        this.title = title;
        this.price = price;
        this.year = year;
        this.description = description;
        this.author = author;
        this.category = category;
        this.photoURL = photoURL;
    }
}
