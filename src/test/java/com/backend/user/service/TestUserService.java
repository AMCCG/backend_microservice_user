package com.backend.user.service;

import com.backend.user.entity.UserEntity;
import com.backend.user.services.UserService;
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
    public void createUser() {
        UserEntity user = new UserEntity();
        user.setId(UserCreateOne.id);
        user.setName(UserCreateOne.name);
        UserEntity result = userService.createUser(user);
        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserCreateOne.id, result.getId());
        Assertions.assertEquals(UserCreateOne.name, result.getName());
    }

    @Test
    @Order(2)
    public void getUserById() throws Exception {
        UserEntity result = userService.getUserById(UserCreateOne.id);
        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(UserCreateOne.id, result.getId());
        Assertions.assertEquals(UserCreateOne.name, result.getName());
    }

    interface UserCreateOne {
        String id = UUID.randomUUID().toString();
        String name = "MongoUser01";
    }
}
