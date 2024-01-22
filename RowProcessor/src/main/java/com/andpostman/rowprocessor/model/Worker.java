package com.andpostman.rowprocessor.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "worker", schema = "bank")
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Worker{

    @Id
    @Column("id")
    private Integer id;

    @Column("account")
    private @NonNull String account;

    @Column("fio")
    private @NonNull String fio;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("account", account)
                .append("fio", fio)
                .toString();
    }

}
