package com.andpostman.packagefinisher.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalTime;

@Table(name = "check_job", schema = "bank")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Status {

    @Id
    @Column("id")
    private Integer id;

    @Column("package_id")
    private @NonNull Integer packageId;

    @Column("insertion_time")
    private @NonNull LocalTime timeStamp;

    @Column("state")
    private @NonNull int state;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("package_id", packageId)
                .append("insertion_time", timeStamp)
                .append("state", state)
                .toString();
    }
}

