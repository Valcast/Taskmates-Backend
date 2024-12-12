package com.drivespace.taskmatesbackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

public class UserNotFoundException extends ErrorResponseException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
