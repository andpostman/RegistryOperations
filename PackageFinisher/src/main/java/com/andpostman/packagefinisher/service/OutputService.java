package com.andpostman.packagefinisher.service;

import reactor.core.publisher.Mono;

public interface OutputService {
    Mono<Void> checkToPrint(int id);
}
