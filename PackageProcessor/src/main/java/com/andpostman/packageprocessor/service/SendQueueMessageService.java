package com.andpostman.packageprocessor.service;

import com.andpostman.packageprocessor.property.JmsRowMessage;
import reactor.core.publisher.Mono;

public interface SendQueueMessageService {
    Mono<Void>sendMessage(JmsRowMessage jmsRowMessage, boolean greenField);
}
