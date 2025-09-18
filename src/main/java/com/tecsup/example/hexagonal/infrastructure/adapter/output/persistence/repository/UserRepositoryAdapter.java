package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {

        // Domain to Entity
        UserEntity userEntity = null; // (UserEntity)user;

        // Save entity
        UserEntity entityCreated = this.jpaRepository.save(userEntity);

        // Entity to Domain
        User userCreated = null; //(User)entityCreated;

        return userCreated;
    }
}
