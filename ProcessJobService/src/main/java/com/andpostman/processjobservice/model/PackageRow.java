package com.andpostman.processjobservice.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table(name = "package_row", schema = "bank" )
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class PackageRow {

    @Id
    @Column("id")
    private Integer id;

    @Column("req_id")
    private @NonNull Integer reqId;

    @Column("account")
    private @NonNull String account;

    @Column("fio")
    private @NonNull String fio;

    @Column("amount")
    private @NonNull BigDecimal amount;

    @Column("green_field")
    private @NonNull boolean greenField;

    @Column("state")
    private @NonNull int state;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("reqId", reqId)
                .append("account", account)
                .append("fio", fio)
                .append("amount", amount)
                .append("greenField", greenField)
                .append("state", state)
                .toString();
    }
}
