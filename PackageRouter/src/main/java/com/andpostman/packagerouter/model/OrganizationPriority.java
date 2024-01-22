package com.andpostman.packagerouter.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "organization_priority", schema = "bank")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "contractNumber")
public class OrganizationPriority{

    @Id
    private String contractNumber;

    @Column("green_field")
    private @NonNull boolean greenField;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("contract_number", contractNumber)
                .append("green_field", greenField)
                .toString();
    }

}
