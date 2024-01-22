package com.andpostman.packageprocessor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.andpostman.packageprocessor.dto.PackageDto;
import com.andpostman.packageprocessor.service.impl.PackageProcessorServiceImpl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PackageProcessorController {

    private final PackageProcessorServiceImpl service;

    @PostMapping("/sendPackageToSplit")
    public Mono<String> sendPackageToSplit(@RequestBody PackageDto packageDto, @RequestHeader(value = "green_field") String greenField){
        log.info("get from package router: '{}', '{}'", packageDto, greenField);
        boolean isGreenField = Boolean.parseBoolean(greenField);
        return service.processPackage(packageDto, isGreenField);
    }
}
