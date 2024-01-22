package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.model.Worker;
import reactor.core.publisher.Mono;

public interface WorkerService {
    Mono<Worker> getWorkerByFio(String fio);
    Mono<Worker> getWorkerByAccount(String account);
}
