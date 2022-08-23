package com.backend.user.business;

import com.backend.user.exception.BaseException;
import com.backend.user.model.UserModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserBusiness {

    @Autowired
    UserBusiness userBusiness;

    @Test
    @Order(1)
    public void givenUser_whenRegister() throws BaseException {
        // Given
        String token = UserLogin.email;
        // When
        UserModel result = userBusiness.register(UserLogin.email, UserLogin.password, UserLogin.userName);
        // Assert
        Assertions.assertEquals(UserLogin.email, result.getEmail());
        Assertions.assertEquals(UserLogin.userName, result.getUserName());
    }

    @Test
    @Order(2)
    public void givenToken_whenLoginPass() throws BaseException {
        // Given
        String token = UserLogin.email;
        // When
        String result = userBusiness.login(UserLogin.email, UserLogin.password);
        // Assert
        Assertions.assertEquals(token, result);
    }


    interface UserLogin {
        String userName = "MongoUser01";
        String email = "mongo_user1@microservice.com";
        String password = "123456";
    }
}
