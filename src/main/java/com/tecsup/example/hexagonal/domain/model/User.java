package com.tecsup.example.hexagonal.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private String lastName;
    private String middleName;
    private Integer dni;
    private String phone;
    private int age;
    private Role role;

    public boolean hasValidDNI() {
        return dni != null &&
                dni > 0;
    }

    // Business logic methods - PURE domain logic!
    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }

    public boolean hasValidName() {
        return name != null &&
                !name.trim().isEmpty() &&
                name.length() >= 2;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

}
