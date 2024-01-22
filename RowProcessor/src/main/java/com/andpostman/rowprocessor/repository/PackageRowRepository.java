package com.andpostman.rowprocessor.repository;

import com.andpostman.rowprocessor.model.PackageRow;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PackageRowRepository extends ReactiveCrudRepository<PackageRow, Integer>{
    @Query(value = "update bank.package_row set state = :state where id = :id ")
    Mono<PackageRow> update (@Param("id") int id, @Param("state") int state);
}
