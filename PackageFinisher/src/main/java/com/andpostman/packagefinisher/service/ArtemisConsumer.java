package com.andpostman.packagefinisher.service;

import com.andpostman.packagefinisher.service.impl.OutputServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import com.andpostman.packagefinisher.property.JmsReceiveMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtemisConsumer {

    private final OutputServiceImpl service;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(JmsReceiveMessage message){
        log.info("Received message from ProcessJob Service = '{}'", message);
        if (message.getPackageId() != 0)
           service.checkToPrint(message.getPackageId()).subscribe();
    }
}
