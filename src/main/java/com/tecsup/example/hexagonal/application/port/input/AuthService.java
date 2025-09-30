package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(String email, String password);

    String generateToken(String email);

}
