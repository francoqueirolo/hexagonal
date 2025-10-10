package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.Role;
import com.tecsup.example.hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    private final Long ID = 100L;
    private final String NAME = "Eder";
    private final String EMAIL = "eder@gmail.com";
    private final String LASTNAME = "Saldana";
    private final String MIDDLENAME = "More";
    private final Integer AGE = 30;
    private final Integer DNI = 21212121;
    private final String PHONE = "955614940";
    private final String PASSWORD = "12345";
    private final boolean ENABLED = true;
    private final Role ROLE = new Role();

    @BeforeEach
    void setup() {
        userService = new UserServiceImpl(userRepository);
    }

    /**
     * Método Auxiliar para crear un objeto User de forma rápida.
     */
    private User createDefaultUser() {
        return new User(
                ID,
                NAME,
                EMAIL,
                PASSWORD,
                ENABLED,
                LASTNAME,
                MIDDLENAME,
                DNI,
                PHONE,
                AGE,
                ROLE
        );
    }

    /**
     * Método Auxiliar para validar todas las propiedades del User.
     */
    private void assertUserFields(User expected, User actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getDni(), actual.getDni());
        assertEquals(expected.getPhone(), actual.getPhone());
    }

    @Test
    void createUser_Success() {
        User newUser = createDefaultUser();
        User savedUser = createDefaultUser();

        when(userRepository.save(newUser)).thenReturn(savedUser);

        User realUser = userService.createUser(newUser);

        assertNotNull(realUser);
        assertUserFields(savedUser, realUser);
    }

    @Test
    void findUserById_Success() {
        User existingUser = createDefaultUser();

        when(userRepository.findById(ID)).thenReturn(Optional.of(existingUser));

        User realUser = userService.findUser(ID);

        assertNotNull(realUser);
        assertUserFields(existingUser, realUser);
    }

    @Test
    void findUserById_NotFound() {
        Long ID_UNKNOW = 999L;

        when(userRepository.findById(ID_UNKNOW)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.findUser(ID_UNKNOW));
    }

    @Test
    void findUserByLastName_Success() {
        User existingUser = createDefaultUser();

        when(userRepository.findByLastName(LASTNAME)).thenReturn(Optional.of(existingUser));

        User realUser = userService.findUserByLastName(LASTNAME);

        assertNotNull(realUser);
        assertUserFields(existingUser, realUser);
    }

    @Test
    void findUserBydocumentNumber_Success() {
        User existingUser = createDefaultUser();
        Integer dniAsInteger = Integer.valueOf(DNI);

        when(userRepository.findByDocumentNumber(dniAsInteger)).thenReturn(Optional.of(existingUser));

        User realUser = userService.findUserByDocumentNumber(dniAsInteger);

        assertNotNull(realUser);
        assertUserFields(existingUser, realUser);
    }

    @Test
    void findUsersByAge_Success() {
        User existingUser = createDefaultUser();
        List<User> users = List.of(existingUser);

        when(userRepository.findByAgeLessThan(AGE)).thenReturn(Optional.of(existingUser));

        List<User> realUsers = userService.findUsersByAge(AGE);

        assertNotNull(realUsers);
        assertFalse(realUsers.isEmpty());

        assertUserFields(existingUser, realUsers.get(0));
    }
}