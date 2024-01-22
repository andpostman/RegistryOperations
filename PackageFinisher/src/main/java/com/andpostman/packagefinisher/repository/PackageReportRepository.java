package com.andpostman.packagefinisher.repository;

import com.andpostman.packagefinisher.model.PackageReport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageReportRepository extends ReactiveCrudRepository<PackageReport, Integer> {
}
