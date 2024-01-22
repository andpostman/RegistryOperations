package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.RowResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowResultRepository extends ReactiveCrudRepository<RowResult, Integer> {

}
