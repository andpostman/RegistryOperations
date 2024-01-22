package com.andpostman.rowprocessor.service;

import reactor.core.publisher.Mono;
import com.andpostman.rowprocessor.model.RowResult;
import com.andpostman.rowprocessor.property.JmsRowMessage;

import java.math.BigDecimal;

public interface RowResultService {
    Mono<RowResult> insertResultData(JmsRowMessage rowInfo, BigDecimal amount);
}
