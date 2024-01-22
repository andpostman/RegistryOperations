package com.andpostman.rowrouter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class JmsErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.warn("spring jms error handling");
        log.error(t.getCause().getMessage());
    }
}
