package com.microservice.backend.user.business;

import com.microservice.backend.user.entity.UserEntity;
import com.microservice.backend.user.exception.BaseException;
import com.microservice.backend.user.mapper.UserMapper;
import com.microservice.backend.user.model.UserResponse;
import com.microservice.backend.user.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserResponse login(String email, String password) throws BaseException {
        UserEntity user = userService.getUserByEmail(email);
        return this.userMapper.toRegisterResponse(user);
    }

    public UserResponse register(String email, String password, String userName) throws BaseException {
        UserEntity user = userService.createUser(email, password, userName);
        return this.userMapper.toRegisterResponse(user);
    }

    public void deleteUser(String email) {
        this.userService.deleteUserByEmail(email);
    }
}
