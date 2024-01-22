package com.andpostman.cronjobservice.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JmsMessage implements Serializable {
    String action;
}
