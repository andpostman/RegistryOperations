package com.andpostman.packageprocessor.service;

import com.andpostman.packageprocessor.dto.PackageDto;
import com.andpostman.packageprocessor.dto.WorkerDto;
import com.andpostman.packageprocessor.model.Package;
import com.andpostman.packageprocessor.model.PackageRow;
import com.andpostman.packageprocessor.model.Status;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PackageProcessorService {
    Mono<String> processPackage(PackageDto packageDto, boolean greenField);
    Mono<Package> insertPackage(PackageDto packageDto, boolean greenField);
    Flux<PackageRow> insertRow(List<WorkerDto> workers, int packageId, boolean greenField);
    Mono<Void> sendRow(PackageRow packageRow, boolean greenField);
    Mono<Status> insertStatus(int packageId);
}
