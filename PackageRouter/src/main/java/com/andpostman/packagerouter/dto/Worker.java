package com.andpostman.packagerouter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "Worker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker implements Serializable {
    private String fio;
    private String account;
    private BigDecimal amount;
}
