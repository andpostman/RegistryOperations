package com.andpostman.processjobservice.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table(name = "package", schema = "bank")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Package {

    @Id
    @Column("id")
    private Integer id;

    @Column("contract_number")
    private @NonNull String contractNumber;

    @Column("organization")
    private @NonNull String organization;

    @Column("total_amount")
    private @NonNull BigDecimal totalAmount;

    @Column("green_field")
    private @NonNull boolean greenField;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("contract_number", contractNumber)
                .append("organization",organization)
                .append("total_amount",totalAmount)
                .append("green_field", greenField)
                .toString();
    }
}
