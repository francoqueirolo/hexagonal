package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    @WithMockUser(roles = "ADMIN")
    void createUser() throws Exception {

        Long ID = 50L;
        String NAME = "Juana";
        String EMAIL = "juana@demo.com";

        // Initial Condition
        UserRequest request = new UserRequest(NAME, EMAIL);
        User newUser =  User.builder()
                .name(NAME)
                .email(EMAIL)
                .build(); //new User(null, NAME, EMAIL); // UserRequest
        User savedUser = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

                //new User(ID, NAME, EMAIL);  // Save UserEntity
        UserResponse response   = new UserResponse(ID, NAME, EMAIL);

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