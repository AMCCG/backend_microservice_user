package com.microservice.backend.user.mapper;

import com.microservice.backend.user.entity.UserEntity;
import com.microservice.backend.user.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toRegisterResponse(UserEntity user);
}
