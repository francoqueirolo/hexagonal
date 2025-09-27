package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        // Aquí puedes inicializar los mocks y el servicio
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {
    }

    @Test
    void findUser() {
        Long ID = 100L;
        String NAME = "Jaime";
        String EMAIL = "jaime@demo.com";

        // Initial Condition
        User existingUser = new User(ID, NAME, EMAIL);

        // Mocking the repository behavior
        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));

        // Execute the service method
        User realUser = userService.findUser(100L);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());

    }
}













