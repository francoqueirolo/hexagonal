package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;
import java.util.List;

public interface UserService {

    User createUser(User newUser);

    User findUser(Long id);

    User findUserByLastName(String lastname);

    User findUserByDocumentNumber(Integer dni);

    List<User> findUsersByAge(Integer age);
}
