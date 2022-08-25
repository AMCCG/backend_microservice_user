package com.microservice.backend.user.exception;

public class UserException extends BaseException {

    public UserException(String message) {
        super("user." + message);
    }

    public static BaseException userNotFound() {
        return new UserException("not.found");
    }

    public static BaseException emailExisting() {
        return new UserException("email.existing");
    }
}
