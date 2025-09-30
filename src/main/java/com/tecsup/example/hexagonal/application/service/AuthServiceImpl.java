package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.AuthResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(String email, String password) {

        return null;
    }

    @Override
    public String generateToken(String email) {
        return "";
    }
}
