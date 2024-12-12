package com.drivespace.taskmatesbackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.ErrorResponseException;

public class ProjectNotFoundException extends ErrorResponseException {

    public ProjectNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
