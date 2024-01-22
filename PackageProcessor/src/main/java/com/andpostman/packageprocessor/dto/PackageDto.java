package com.andpostman.packageprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PackageDto {
    private String organization;
    private String contractNumber;
    private BigDecimal totalAmount;
    private List<WorkerDto> workers;
}
