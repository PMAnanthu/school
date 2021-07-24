package com.school.studentmanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFountException extends Exception{
    public UserNotFountException(String format) {
        super(format);
    }
}
