package com.andpostman.rowprocessor.service.impl;

import com.andpostman.rowprocessor.model.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.repository.WorkerRepository;
import com.andpostman.rowprocessor.service.WorkerService;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository repository;

    @Override
    public Mono<Worker> getWorkerByFio(String fio) {
        return repository.findWorkerByFio(fio).defaultIfEmpty(new Worker());
    }

    @Override
    public Mono<Worker> getWorkerByAccount(String account) {
        return repository.findWorkerByAccount(account).defaultIfEmpty(new Worker());
    }
}
