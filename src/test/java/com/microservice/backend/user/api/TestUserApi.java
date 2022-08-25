package com.microservice.backend.user.api;

import com.microservice.backend.user.business.UserBusiness;
import com.microservice.backend.user.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApi.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserApi {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserBusiness userBusiness;


    @Test
    public void shouldReturnTokenWhenLoginPass() throws Exception {
        UserEntity user = new UserEntity();
        user.setEmail(UserLogin.email);
        user.setPassword(UserLogin.password);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(user);

        when(userBusiness.login(UserLogin.email, UserLogin.password)).thenReturn(UserLogin.email);
        this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(UserLogin.email)));
    }

    interface UserLogin {
        String userName = "MongoUser01";
        String email = "mongo_user1@microservice.com";
        String password = "123456";
    }
}
