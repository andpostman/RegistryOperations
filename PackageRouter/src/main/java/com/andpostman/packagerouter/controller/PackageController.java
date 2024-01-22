package com.andpostman.packagerouter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.andpostman.packagerouter.dto.Package;
import com.andpostman.packagerouter.service.PackageServiceImpl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PackageController {

    private final PackageServiceImpl service;

    @PostMapping(value = "/sendPackage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> sendPackage(@RequestBody Package aPackage){
        log.info("Get package from json request: '{}'", aPackage);
        return service.sendPackage(aPackage);
    }

    @PostMapping(value = "/sendPackageXml")
    public Mono<String> sendPackageXml(@RequestBody Package aPackage){
        log.info("Get package from xml request: '{}'", aPackage);
        return service.sendPackage(aPackage);
    }
}
