package com.andpostman.packagefinisher.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JmsReceiveMessage implements Serializable {
    int packageId;
}
