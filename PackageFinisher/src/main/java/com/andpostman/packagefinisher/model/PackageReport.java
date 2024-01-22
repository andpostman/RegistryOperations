package com.andpostman.packagefinisher.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalTime;

@Table(name = "package_report", schema = "bank")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class PackageReport {

    @Id
    @Column("id")
    private Integer id;

    @Column("send_amount")
    private @NonNull BigDecimal sendAmount;

    @Column("credited_amount")
    private @NonNull BigDecimal creditedAmount;

    @Column("not_credited_amount")
    private @NonNull BigDecimal notCreditedAmount;

    @Column("insertion_time")
    private @NonNull LocalTime insertionTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Отправлено", sendAmount)
                .append("Зачислено",creditedAmount)
                .append("Не зачислено",notCreditedAmount)
                .append("Время",insertionTime)
                .toString();
    }
}
