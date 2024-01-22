package com.andpostman.rowrouter.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JmsRowMessage implements Serializable {
    private int rowId;
    private int reqId;
    private String account;
    private String fio;
    private BigDecimal amount;
    private PackageRowOperationType type;
}

