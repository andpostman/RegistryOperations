package com.andpostman.rowprocessor.service.impl;

import com.andpostman.rowprocessor.command.PackageRowOperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.model.PackageRow;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.PackageRowRepository;
import com.andpostman.rowprocessor.service.PackageRowService;

@Service
@RequiredArgsConstructor
public class PackageRowServiceImpl implements PackageRowService {

    private final PackageRowRepository repository;

    @Override
    public Mono<PackageRow> updateState(JmsRowMessage row, PackageRowOperationType packageRowOperationType) {
        return repository.update(row.getRowId(), packageRowOperationType.getCode());
    }
}
