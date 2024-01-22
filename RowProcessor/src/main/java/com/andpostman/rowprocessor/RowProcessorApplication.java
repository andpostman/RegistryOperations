package com.andpostman.rowprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class RowProcessorApplication {

    public static void main(String[] args) {
//        ReactorDebugAgent.init();
        SpringApplication.run(RowProcessorApplication.class, args);
    }

}
