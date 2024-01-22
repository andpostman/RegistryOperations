package com.andpostman.packageprocessor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.andpostman.packageprocessor.model.Status;

@Repository
public interface StatusRepository extends ReactiveCrudRepository<Status, Integer> {
}
