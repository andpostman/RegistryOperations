package com.andpostman.packageprocessor.command;

import com.andpostman.packageprocessor.property.JmsRowMessage;
import reactor.core.publisher.Mono;

public interface PackageRowOperation {
    Mono<Void> send(JmsRowMessage row, boolean greenField);
    PackageRowOperationType getType();
}
