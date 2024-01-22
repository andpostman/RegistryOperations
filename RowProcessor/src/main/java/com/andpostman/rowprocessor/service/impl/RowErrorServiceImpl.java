package com.andpostman.rowprocessor.service.impl;

import com.andpostman.rowprocessor.model.RowError;
import com.andpostman.rowprocessor.service.RowErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.RowErrorRepository;

@Service
@RequiredArgsConstructor
public class RowErrorServiceImpl implements RowErrorService {

    private final RowErrorRepository repository;

    @Override
    public Mono<RowError> insertErrorData(JmsRowMessage rowInfo, String description) {
        RowError error = new RowError(rowInfo.getRowId(), description);
        return repository.save(error);
    }
}