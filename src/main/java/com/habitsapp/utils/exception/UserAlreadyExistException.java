package com.habitsapp.utils.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
