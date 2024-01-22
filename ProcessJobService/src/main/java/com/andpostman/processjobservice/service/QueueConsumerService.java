package com.andpostman.processjobservice.service;

import com.andpostman.processjobservice.property.JmsReceiveMessage;
import reactor.core.publisher.Mono;

public interface QueueConsumerService {
    Mono<Void> check(JmsReceiveMessage message);
}
