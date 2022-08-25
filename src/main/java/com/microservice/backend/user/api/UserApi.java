package com.microservice.backend.user.api;


import com.microservice.backend.user.business.UserBusiness;
import com.microservice.backend.user.entity.UserEntity;
import com.microservice.backend.user.exception.BaseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class UserApi {

    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) throws BaseException {
        log.info(userEntity.getEmail());
        log.info(userEntity.getPassword());
        String token = this.userBusiness.login(userEntity.getEmail(), userEntity.getPassword());
        log.info(token);
        return new ResponseEntity(token, HttpStatus.OK);
    }
}

