package com.andpostman.packagefinisher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import com.andpostman.packagefinisher.property.JmsReceiveMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJms
public class MessagingConfig {

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("pck", JmsReceiveMessage.class);
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }

}
