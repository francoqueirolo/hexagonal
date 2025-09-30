package com.tecsup.example.hexagonal.application.port.input;

public interface AuthService {

    void login(String email, String password);

    String generateToken(String email);

}
