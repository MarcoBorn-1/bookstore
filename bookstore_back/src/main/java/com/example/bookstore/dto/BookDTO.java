package com.example.bookstore.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class BookDTO {
    private String title;
    private String author_name;
    private String author_surname;
    private String category_name;
    private int price;
    private int year;
    private String description;
}
