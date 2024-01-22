package com.andpostman.rowprocessor.service;

import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.property.JmsRowMessage;

public interface CommandProcessService {
    Mono<Void> executeCommand(JmsRowMessage rowInfo);
}
