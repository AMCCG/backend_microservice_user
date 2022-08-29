package com.microservice.backend.user.model;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String email;
    private String password;

}
