package com.andpostman.processjobservice.command;

import reactor.core.publisher.Mono;
import com.andpostman.processjobservice.property.JmsSendMessage;

public interface PackageOperation {
    Mono<Void> send(JmsSendMessage row);
    PackageOperationType getType();
}
