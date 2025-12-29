package edu.raijin.insight.dimension.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class RoleReport {

    private UUID employeeId;

    private String fullName;

    private String email;

    private String role;

    private Instant hiredAt;

    private Instant terminatedAt;

    private Integer subtotal;

    private Integer total;

    public static RoleReport ofSubtotal(Integer subtotal) {
        return RoleReport.builder().subtotal(subtotal).build();
    }

    public static RoleReport ofTotal(Integer total) {
        return RoleReport.builder().total(total).build();
    }
}
