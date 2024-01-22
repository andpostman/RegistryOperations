package com.andpostman.packagerouter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "Package")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package implements Serializable {
    private String organization;
    private String contractNumber;
    private BigDecimal totalAmount;
    @XmlElement(name = "Worker")
    @XmlElementWrapper(name = "Workers")
    private List<Worker> workers;
}
