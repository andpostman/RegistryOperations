package com.andpostman.processjobservice.config;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import com.andpostman.processjobservice.property.JmsReceiveMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import com.andpostman.processjobservice.property.JmsSendMessage;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
public class MessagingConfig {

    @Value("${jms.queue.destination.send}")
    private String destinationQueue;

    @Bean
    public Destination packageQueue(){
        return new ActiveMQQueue(destinationQueue);
    }

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("row", JmsReceiveMessage.class);
        typeIdMappings.put("pck", JmsSendMessage.class);
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }

}
