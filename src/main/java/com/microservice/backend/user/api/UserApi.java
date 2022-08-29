package com.microservice.backend.user.api;


import com.microservice.backend.user.business.UserBusiness;
import com.microservice.backend.user.exception.BaseException;
import com.microservice.backend.user.model.UserRequest;
import com.microservice.backend.user.model.UserResponse;
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
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) throws BaseException {
        log.info(userRequest.getEmail());
        log.info(userRequest.getPassword());
        String token = this.userBusiness.login(userRequest.getEmail(), userRequest.getPassword());
        log.info(token);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) throws BaseException {
        UserResponse userResponse = this.userBusiness.register(userRequest.getEmail(), userRequest.getPassword(), userRequest.getUserName());
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}

