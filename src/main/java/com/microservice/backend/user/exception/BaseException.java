package com.microservice.backend.user.exception;

public abstract class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }
}
