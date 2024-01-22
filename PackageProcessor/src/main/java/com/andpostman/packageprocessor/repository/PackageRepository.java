package com.andpostman.packageprocessor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.andpostman.packageprocessor.model.Package;

@Repository
public interface PackageRepository extends ReactiveCrudRepository<Package, Integer> {
}
