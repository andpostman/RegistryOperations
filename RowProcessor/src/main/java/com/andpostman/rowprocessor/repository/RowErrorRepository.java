package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.RowError;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowErrorRepository extends ReactiveCrudRepository<RowError, Integer> {

}
