package com.taskmates.backend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

public class TaskNotFoundException extends ErrorResponseException {
    public TaskNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
