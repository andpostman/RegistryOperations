package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.Worker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WorkerRepository extends ReactiveCrudRepository<Worker, Integer> {

        Mono<Worker> findWorkerByAccount(String account);
        Mono<Worker> findWorkerByFio(String fio);
}
