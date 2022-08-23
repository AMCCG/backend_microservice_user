package com.backend.user.business;

import com.backend.user.entity.UserEntity;
import com.backend.user.exception.BaseException;
import com.backend.user.model.UserModel;
import com.backend.user.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private final UserService userService;

    public UserBusiness(UserService userService) {
        this.userService = userService;
    }

    public String login(String email, String password) throws BaseException {
        UserEntity user = userService.getUserByEmail(email);
        return user.getEmail();
    }

    public UserModel register(String email, String password, String userName) throws BaseException {
        UserEntity user = userService.createUser(email, password, userName);
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setEmail(user.getEmail());
        return userModel;
    }
}
