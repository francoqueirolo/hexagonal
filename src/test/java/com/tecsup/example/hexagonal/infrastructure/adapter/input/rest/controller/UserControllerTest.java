package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    //@Test
    void createUser() throws Exception {

        Long ID = 50L;
        String NAME = "Juana";
        String EMAIL = "juana@demo.com";

        // Initial Condition
        UserRequest request = new UserRequest(
                NAME,
                EMAIL,
                "juana",
                true,
                "Diaz",
                "Juanita",
                12345678,
                "1234567890",
                25
                //Role.ADMIN
        );
        User newUser = User.builder()
                .name(NAME)
                .email(EMAIL)
                .password("juana")
                .enabled(true)
                .lastName("Diaz")
                .middleName("Juanita")
                .dni(12345678)
                .phone("1234567890")
                .age(25)
                //.role(Role.ADMIN)
                .build();

        User savedUser = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        UserResponse response = new UserResponse(
                ID,
                NAME,
                EMAIL,
                "root",
                true,
                "Juanita",
                "Diaz",
                1234567890,
                "1234567890",
                25
        );

        // Mocking the repository behavior
        when(userMapper.toDomain(request)).thenReturn(newUser);
        when(userService.createUser(newUser)).thenReturn(savedUser);
        when(userMapper.toResponse(savedUser)).thenReturn(response);

        // Execute the mvc method
        this.mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andDo(print());

    }

}