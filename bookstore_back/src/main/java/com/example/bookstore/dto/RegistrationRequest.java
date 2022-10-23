package com.example.bookstore.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class RegistrationRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String city;
    private String street;
}
