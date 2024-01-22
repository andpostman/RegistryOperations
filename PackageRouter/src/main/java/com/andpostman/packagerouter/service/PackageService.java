package com.andpostman.packagerouter.service;

import reactor.core.publisher.Mono;
import com.andpostman.packagerouter.dto.Package;
import com.andpostman.packagerouter.model.OrganizationPriority;

public interface PackageService {
    Mono<String> sendPackage(Package aPackage);
    Mono<OrganizationPriority> findPriority(String contractNumber);
    Mono<String> sendPriority(OrganizationPriority pr, Package aPackage);
}
