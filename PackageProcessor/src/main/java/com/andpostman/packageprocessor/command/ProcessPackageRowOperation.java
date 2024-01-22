package com.andpostman.packageprocessor.command;

import com.andpostman.packageprocessor.property.JmsRowMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.jms.Destination;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessPackageRowOperation implements PackageRowOperation {

    private final JmsTemplate jmsTemplate;
    private final Destination destinationQueue;

    @Override
    public Mono<Void> send(JmsRowMessage message, boolean greenField) {
        jmsTemplate.convertAndSend(destinationQueue, message, m -> {
            m.setBooleanProperty("green_filed", greenField);
            return m;
        });
        log.info("Sent message to Route Processor = '{}','{}'", message,greenField);
        return Mono.empty();
    }

    @Override
    public PackageRowOperationType getType() {
        return PackageRowOperationType.PROCESSING;
    }
}
