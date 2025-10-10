package com.tecsup.example.hexagonal.application.port.output;

import com.tecsup.example.hexagonal.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLastName(String lastName);

    Optional<User> findByAgeLessThan(int age);

    Optional<User> findByDocumentNumber(Integer dni);
}
