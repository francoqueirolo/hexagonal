package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private String lastName;
    private String middleName;
    private Integer dni;
    private String phone;
    private Integer age;
}
