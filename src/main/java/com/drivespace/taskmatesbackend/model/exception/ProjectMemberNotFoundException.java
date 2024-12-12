package com.drivespace.taskmatesbackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

public class ProjectMemberNotFoundException extends ErrorResponseException {
    public ProjectMemberNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
