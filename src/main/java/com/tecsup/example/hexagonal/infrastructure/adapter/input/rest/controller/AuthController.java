package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public void login(String email, String password) {
        log.info("Login attempt for email: {}", email);
        authService.login(email, password);
    }

}
