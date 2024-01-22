package com.andpostman.rowrouter.service;

import com.andpostman.rowrouter.property.JmsRowMessage;
import reactor.core.publisher.Mono;

public interface QueueConsumerService {
    Mono<Void> sendMessageToRowProcessor(JmsRowMessage rowInfo, boolean greenField);
}
