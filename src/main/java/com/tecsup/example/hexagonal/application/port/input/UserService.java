package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;

public interface UserService {

    User createUser(User newUser);

    User findUser(Long id);
}
