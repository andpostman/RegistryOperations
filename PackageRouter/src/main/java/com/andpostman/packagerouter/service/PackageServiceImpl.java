package com.andpostman.packagerouter.service;

import com.andpostman.packagerouter.exception.NotFoundDBFieldException;
import com.andpostman.packagerouter.exception.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.andpostman.packagerouter.config.WebClientConfig;
import com.andpostman.packagerouter.dto.Package;
import com.andpostman.packagerouter.model.OrganizationPriority;
import com.andpostman.packagerouter.repository.OrganizationPriorityRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageServiceImpl implements PackageService{

    private final OrganizationPriorityRepository repository;
    private final WebClientConfig client;

    @Override
    public Mono<String> sendPackage(Package aPackage) {
        return findPriority(aPackage.getContractNumber())
                .flatMap(resp -> sendPriority(resp,aPackage));

    }

    @Override
    public Mono<OrganizationPriority> findPriority(String contractNumber){
        log.info("requesting {} to db", contractNumber);
        return repository.findById(contractNumber).switchIfEmpty(Mono.error(NotFoundDBFieldException::new));
    }

    @Override
    public Mono<String> sendPriority(OrganizationPriority pr, Package aPackage){
        log.info("sending priority {}", pr);
        WebClient.ResponseSpec resp = client.callPackageProcessor().post()
                .uri("/sendPackageToSplit")
                .bodyValue(aPackage)
                .header("green_field", String.valueOf(pr.isGreenField()))
                .retrieve();
        resp.onStatus(HttpStatus::isError, response -> response.createException()
                .flatMap(e -> Mono.error(new ServiceUnavailableException(e.getResponseBodyAsString()))));
        return resp.bodyToMono(String.class);
    }

}
