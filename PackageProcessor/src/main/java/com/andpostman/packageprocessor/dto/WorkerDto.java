package com.andpostman.packageprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WorkerDto {
    private String fio;
    private String account;
    private BigDecimal amount;
}
