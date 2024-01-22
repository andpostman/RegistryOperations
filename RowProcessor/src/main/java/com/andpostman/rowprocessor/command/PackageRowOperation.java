package com.andpostman.rowprocessor.command;

import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.property.JmsRowMessage;

public interface PackageRowOperation {
    Mono<Void> send(JmsRowMessage row);
    PackageRowOperationType getType();
}
