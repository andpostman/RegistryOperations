package com.andpostman.packagefinisher.repository;

import com.andpostman.packagefinisher.model.Package;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PackageRepository extends ReactiveCrudRepository<Package, Integer> {
    Mono<Package> getPackageById(int id);
}
