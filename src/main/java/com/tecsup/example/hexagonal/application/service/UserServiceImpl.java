package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User newUser) {

        // Validation logic can be added here
        validateUserInput(newUser);

        // Save the user using the repository
        User user = this.userRepository.save(newUser);

        //user.setName("Margot"); // Garbage line for testing purposes

        return user;

    }



    @Override
    public User findUser(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        User user = this.userRepository.findById(id)
                .orElseThrow( ()-> new UserNotFoundException(id) );

        return user;
    }


    private void validateUserInput(User newUser) {

        if (!newUser.hasValidName())
            throw new InvalidUserDataException("Invalid email");

        if (!newUser.hasValidEmail())
            throw new InvalidUserDataException("Invalid email");


    }
}
