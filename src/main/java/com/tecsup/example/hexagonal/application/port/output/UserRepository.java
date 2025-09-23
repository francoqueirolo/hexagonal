package com.tecsup.example.hexagonal.application.port.output;

import com.tecsup.example.hexagonal.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
