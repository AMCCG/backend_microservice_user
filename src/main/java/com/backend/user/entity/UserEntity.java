package com.backend.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@Data
public class UserEntity {

    @Id
    private String id;

    private String userName;
    private String email;
    private String password;
}
