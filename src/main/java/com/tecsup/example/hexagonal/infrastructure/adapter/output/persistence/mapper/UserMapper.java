package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.mapper;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity.UserEntity;

public class UserMapper {


    // Domain to Entity
    //UserEntity userEntity = null; // (UserEntity)user;

    /**
     * Convert UserEntity to User domain
     * @param domain
     * @return
     */
    public static UserEntity toEntity(User domain){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(domain.getId());
        userEntity.setName(domain.getName());
        userEntity.setEmail(domain.getEmail());
        return userEntity;
    }

    // User userCreated = null; //(User)entityCreated;

    /**
     * Convert UserEntity to User domain
     * @param entity
     * @return
     */
    public static User toDomain(UserEntity entity){
        return new User(entity.getId(),
                entity.getName(),entity.getEmail());
    }


}
