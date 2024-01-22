package com.andpostman.packagefinisher.repository;

import com.andpostman.packagefinisher.model.Status;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatusRepository extends ReactiveCrudRepository<Status, Integer> {
    @Query(value = "update bank.check_job set state = :state where package_id = :package_id ")
    Mono<Void> update (@Param("package_id") int packageId, @Param("state") int state);
}
