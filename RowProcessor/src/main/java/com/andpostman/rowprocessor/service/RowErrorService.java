package com.andpostman.rowprocessor.service;

import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.model.RowError;
import com.andpostman.rowprocessor.property.JmsRowMessage;

public interface RowErrorService {
    Mono<RowError> insertErrorData(JmsRowMessage rowInfo, String description);
}
