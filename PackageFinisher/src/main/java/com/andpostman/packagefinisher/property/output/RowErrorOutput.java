package com.andpostman.packagefinisher.property.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RowErrorOutput {

    private String account;
    private String description;
    private BigDecimal amount;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("account", account)
                .append("description", description)
                .append("amount", amount)
                .toString();
    }
}
