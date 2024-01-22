package com.andpostman.rowprocessor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundFieldException.class)
    public String notFoundFieldHandler(NotFoundFieldException exception){
        log.error(exception.getMessage());
        return exception.getMessage();
    }
}
