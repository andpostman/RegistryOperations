package com.andpostman.rowrouter.service.impl;

import com.andpostman.rowrouter.property.JmsRowMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowrouter.config.WebClientConfig;
import com.andpostman.rowrouter.service.QueueConsumerService;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueConsumerServiceImpl implements QueueConsumerService {

    private final WebClientConfig client;

    @Override
    public Mono<Void> sendMessageToRowProcessor(JmsRowMessage rowInfo, boolean greenField) {
        log.info("Sending message to RowProcessor. Header: green_field = {} Body: {}", greenField, rowInfo);
        return client.callRowProcessor().post()
                .uri("/processCommand")
                .bodyValue(rowInfo)
                .header("green_field", String.valueOf(greenField))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
