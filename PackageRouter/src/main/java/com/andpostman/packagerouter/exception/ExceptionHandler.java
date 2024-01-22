package com.andpostman.packagerouter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundDBFieldException.class)
    public String notFoundDBFieldHandler(NotFoundDBFieldException exception){
        log.error(exception.getMessage());
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceUnavailableException.class)
    public String notAvailableServiceHandler(ServiceUnavailableException exception){
        log.error(exception.getMessage());
        return exception.getMessage();
    }

}
