package com.andpostman.rowprocessor.service.impl;

import com.andpostman.rowprocessor.service.RowResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.model.RowResult;
import com.andpostman.rowprocessor.property.JmsRowMessage;
import com.andpostman.rowprocessor.repository.RowResultRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RowResultServiceImpl implements RowResultService {

    private final RowResultRepository repository;

    @Override
    public Mono<RowResult> insertResultData(JmsRowMessage rowInfo, BigDecimal amount) {
        RowResult result = new RowResult(rowInfo.getRowId(), amount);
        return repository.save(result);
    }
}