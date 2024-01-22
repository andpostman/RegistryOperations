package com.andpostman.cronjobservice;

import com.andpostman.cronjobservice.service.ArtemisProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CronJobServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CronJobServiceApplication.class, args);
        ArtemisProducer producer = context.getBean(ArtemisProducer.class);
        producer.sendMessage();
        context.close();
    }

}
