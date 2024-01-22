package com.andpostman.processjobservice.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.andpostman.processjobservice.property.JmsSendMessage;
import javax.jms.Destination;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivePackageOperation implements PackageOperation {

    private final JmsTemplate jmsTemplate;
    private final Destination destinationQueue;

    @Override
    public Mono<Void> send(JmsSendMessage message) {
        jmsTemplate.convertAndSend(destinationQueue, message);
        log.info("Sent message to package finisher ='{}'", message);
        return Mono.empty();
    }

    @Override
    public PackageOperationType getType() {
        return PackageOperationType.ACTIVE;
    }
}
