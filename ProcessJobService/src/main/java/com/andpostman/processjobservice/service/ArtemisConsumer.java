package com.andpostman.processjobservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import com.andpostman.processjobservice.property.JmsReceiveMessage;
import com.andpostman.processjobservice.service.impl.QueueConsumerServiceImpl;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtemisConsumer {

    private static final String ACTION_NAME = "status";
    private final QueueConsumerServiceImpl service;

    @JmsListener(destination = "${jms.queue.destination.listen}")
    public void receive(JmsReceiveMessage message){
        log.info("Received message='{}'", message);
        if(message.getAction().equals(ACTION_NAME))
            service.check(message).subscribe();
    }


}