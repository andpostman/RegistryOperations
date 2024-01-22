package com.andpostman.processjobservice.repository;

import com.andpostman.processjobservice.model.PackageRow;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRowRepository extends ReactiveCrudRepository<PackageRow, Integer> {
}

