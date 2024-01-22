package com.andpostman.processjobservice.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table(name = "row_result", schema = "bank")
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class RowResult {

    @Id
    @Column("id")
    private Integer id;

    @Column("row_id")
    private @NonNull Integer rowId;

    @Column("amount")
    private @NonNull BigDecimal amount;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("row_id", rowId)
                .append("amount", amount)
                .toString();
    }

}
