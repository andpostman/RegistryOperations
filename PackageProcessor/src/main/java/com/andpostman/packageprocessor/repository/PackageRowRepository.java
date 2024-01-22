package com.andpostman.packageprocessor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.andpostman.packageprocessor.model.PackageRow;

@Repository
public interface PackageRowRepository extends ReactiveCrudRepository<PackageRow, Integer> {
}
