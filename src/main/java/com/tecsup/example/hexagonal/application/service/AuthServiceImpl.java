package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public void login(String email, String password) {

    }

    @Override
    public String generateToken(String email) {
        return "";
    }
}
