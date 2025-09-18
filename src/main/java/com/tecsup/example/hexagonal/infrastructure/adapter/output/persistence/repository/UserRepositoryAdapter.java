package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {

        // Domain to Entity
        UserEntity userEntity = UserMapper.toEntity(user); // (UserEntity)user;

        // Save entity
        UserEntity entityCreated = this.jpaRepository.save(userEntity);

        // Entity to Domain
        User userCreated = UserMapper.toDomain(entityCreated); //(User)entityCreated;

        return userCreated;
    }
}
