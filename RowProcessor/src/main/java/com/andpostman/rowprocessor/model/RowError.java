package com.andpostman.rowprocessor.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "row_error", schema = "bank")
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class RowError implements Persistable<Integer> {

    @Id
    @Column("id")
    private Integer id;

    @Column("row_id")
    private @NonNull Integer rowId;

    @Column("description")
    private @NonNull String description;

    @Transient
    public boolean newError;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("row_id", rowId)
                .append("description", description)
                .toString();
    }

    @Override
    @Transient
    public boolean isNew() {
        return this.newError || id == null;
    }

    public RowError setAsNew(){
        this.newError = true;
        return this;
    }
}