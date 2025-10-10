package com.tecsup.example.hexagonal.domain.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }

    public UserNotFoundException(Integer userDNI) {
        super("User not found with DNI: " + userDNI);
    }

    public UserNotFoundException(String lastname) {
        super("User not found with lastname: " + lastname);
    }
}
