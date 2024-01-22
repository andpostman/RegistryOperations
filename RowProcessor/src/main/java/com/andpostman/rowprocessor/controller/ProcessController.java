package com.andpostman.rowprocessor.controller;

import com.andpostman.rowprocessor.service.impl.CommandProcessServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.property.JmsRowMessage;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProcessController {

    private final CommandProcessServiceImpl service;

    @PostMapping("/processCommand")
    public Mono<Void> processCommand(@RequestBody JmsRowMessage rowInfo, @RequestHeader(value = "green_field") String header){
        log.info("Command handler get from Route Processor header: '{}' message: '{}'",header,rowInfo);
        return service.executeCommand(rowInfo);
    }
}
