package com.taskmates.backend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

public class UserAlreadyInvitedException extends ErrorResponseException {
    public UserAlreadyInvitedException() {
        super(HttpStatus.CONFLICT);
    }
}
