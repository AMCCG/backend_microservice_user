package com.microservice.backend.user.service;

import com.microservice.backend.user.entity.UserEntity;
import com.microservice.backend.user.exception.BaseException;
import com.microservice.backend.user.exception.UserException;
import com.microservice.backend.user.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    UserService userService;

    @Test
    @Order(1)
    public void createUser() throws BaseException {
        UserEntity result = userService.createUser(UserCreateOne.email, UserCreateOne.password, UserCreateOne.userName);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserCreateOne.userName, result.getUserName());
        Assertions.assertEquals(UserCreateOne.email, result.getEmail());
        Assertions.assertEquals(UserCreateOne.password, result.getPassword());
    }

    @Test
    @Order(2)
    public void givenUserException_whenCreateUserWithEmailExisting() throws BaseException {
        // Assert
        Assertions.assertThrows(UserException.class, () -> userService.createUser(UserCreateOne.email, UserCreateOne.password, UserCreateOne.userName));
    }

    @Test
    @Order(3)
    public void givenUserException_whenGetUserById() {
        String id = "123456789";
        // Assert
        Assertions.assertThrows(UserException.class, () -> userService.getUserById(id));
    }

    @Test
    @Order(4)
    public void givenUser_whenGetUserByEmail() throws BaseException {
        UserEntity result = userService.getUserByEmail(UserCreateOne.email);
        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserCreateOne.userName, result.getUserName());
        Assertions.assertEquals(UserCreateOne.email, result.getEmail());
        Assertions.assertEquals(UserCreateOne.password, result.getPassword());
    }


    @Test
    @Order(5)
    public void whenDeleteUserByEmail() {
        Assertions.assertDoesNotThrow(() -> userService.deleteUserByEmail(UserCreateOne.email));
    }

    interface UserCreateOne {
        String id = UUID.randomUUID().toString();
        String userName = "MongoUser01";
        String email = "mongo_user1@microservice.com";
        String password = "123456";
    }
}
