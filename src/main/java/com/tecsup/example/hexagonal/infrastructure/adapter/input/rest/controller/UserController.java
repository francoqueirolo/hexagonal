package com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.controller;


import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infrastructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {

        log.info("Creating request with name: {} and email: {}", request.getName(), request.getEmail());

        User newUser = this.userMapper.toDomain(request);

        User createUser = this.userService.createUser(newUser);

        UserResponse response = this.userMapper.toResponse(createUser);

        return response;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {

        User user = this.userService.findUser(id);

        UserResponse response = this.userMapper.toResponse(user);

        return response;

    }


}
