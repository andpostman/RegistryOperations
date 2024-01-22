package com.andpostman.processjobservice.repository;

import com.andpostman.processjobservice.model.Status;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StatusRepository extends ReactiveCrudRepository<Status, Integer> {
    Flux<Status> getStatusByState(int state);
}
