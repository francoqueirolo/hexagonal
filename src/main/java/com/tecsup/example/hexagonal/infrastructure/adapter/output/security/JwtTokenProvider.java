package com.tecsup.example.hexagonal.infrastructure.adapter.output.security;


import com.tecsup.example.hexagonal.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {


    public String generateToken(User user) {
        // Aquí iría la lógica para crear un token JWT real
        log.info("Creating token for email: {}", user.getEmail());
        return "mocked-jwt-token-for-" + user.getEmail();
    }

}
