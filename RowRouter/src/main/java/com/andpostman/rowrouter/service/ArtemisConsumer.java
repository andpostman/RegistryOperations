package com.andpostman.rowrouter.service;

import com.andpostman.rowrouter.property.JmsRowMessage;
import com.andpostman.rowrouter.service.impl.QueueConsumerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtemisConsumer {

    private final QueueConsumerServiceImpl service;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(JmsRowMessage message,
                        @Headers MessageHeaders headers){
        boolean greenField = Boolean.parseBoolean(headers.get("green_field",String.class));
        log.info("Received message from Package Processor = '{},{}'", headers, message);
        service.sendMessageToRowProcessor(message,greenField).subscribe();
    }
}
