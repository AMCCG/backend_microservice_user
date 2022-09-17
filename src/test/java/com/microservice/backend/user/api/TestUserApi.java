package com.microservice.backend.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.backend.user.business.UserBusiness;
import com.microservice.backend.user.model.UserRequest;
import com.microservice.backend.user.model.UserResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApi.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserApi {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBusiness userBusiness;


    @Test
    @Order(1)
    public void shouldReturnNewUserWhenRegister() throws Exception {
        UserRequest user = new UserRequest();
        user.setEmail(UserLogin.email);
        user.setPassword(UserLogin.password);
        user.setUserName(UserLogin.userName);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(UserLogin.email);
        userResponse.setUserName(UserLogin.userName);
        when(userBusiness.register(UserLogin.email, UserLogin.password, UserLogin.userName)).thenReturn(userResponse);

        this.mockMvc.perform(post("/backend/user/register").contentType(APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.email").value(UserLogin.email));
    }

    @Test
    @Order(2)
    public void shouldReturnTokenWhenLoginPass() throws Exception {
        UserRequest user = new UserRequest();
        user.setEmail(UserLogin.email);
        user.setPassword(UserLogin.password);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(UserLogin.email);
        userResponse.setUserName(UserLogin.userName);

        when(userBusiness.login(UserLogin.email, UserLogin.password)).thenReturn(userResponse);
        this.mockMvc.perform(post("/backend/user/login").contentType(APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.email").value(UserLogin.email));
    }

    interface UserLogin {
        String userName = "MongoUser01";
        String email = "mongo_user1@microservice.com";
        String password = "123456";
    }
}
