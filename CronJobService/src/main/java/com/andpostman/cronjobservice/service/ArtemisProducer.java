package com.andpostman.cronjobservice.service;

import com.andpostman.cronjobservice.property.JmsMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtemisProducer {

    private final JmsTemplate template;
    private final Destination destination;

    public void sendMessage(){
        template.convertAndSend(destination, new JmsMessage("status"));
        log.info("Message sent to ProcessJob Service");
    }
}
