package com.andpostman.packagerouter.repository;

import com.andpostman.packagerouter.model.OrganizationPriority;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationPriorityRepository extends ReactiveCrudRepository<OrganizationPriority, String> {
}
