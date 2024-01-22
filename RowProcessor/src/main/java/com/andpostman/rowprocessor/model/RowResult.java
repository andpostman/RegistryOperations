package com.andpostman.rowprocessor.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;


@Table(name = "row_result", schema = "bank")
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class RowResult implements Persistable<Integer> {

    @Id
    @Column("id")
    private Integer id;

    @Column("row_id")
    private @NonNull Integer rowId;

    @Column("amount")
    private @NonNull BigDecimal amount;

    @Transient
    private boolean newRow;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("row_id", rowId)
                .append("amount", amount)
                .toString();
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newRow || id == null;
    }

    public RowResult setAsNew(){
        this.newRow = true;
        return this;
    }
}