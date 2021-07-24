package com.school.studentmanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidInputException extends Exception{
    public NotValidInputException(String format) {
        super(format);
    }
}
