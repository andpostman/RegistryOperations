package com.andpostman.packagefinisher.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import com.andpostman.packagefinisher.model.PackageRow;

@Repository
public interface PackageRowRepository extends ReactiveCrudRepository<PackageRow, Integer> {
    Flux<PackageRow> findByReqId(int reqId);
}
