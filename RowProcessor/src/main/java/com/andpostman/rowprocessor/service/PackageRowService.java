package com.andpostman.rowprocessor.service;

import com.andpostman.rowprocessor.command.PackageRowOperationType;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.model.PackageRow;
import com.andpostman.rowprocessor.property.JmsRowMessage;

public interface PackageRowService {
    Mono<PackageRow> updateState(JmsRowMessage row, PackageRowOperationType packageRowOperationType);
}
