package com.example.bookstore.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class LoginRequest {
    private String email;
    private String password;
}
