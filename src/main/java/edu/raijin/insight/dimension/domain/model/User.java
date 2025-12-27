package edu.raijin.insight.dimension.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
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
public class User {

    private UUID userId;

    private String fullName;

    private String email;

    private String role;

    private Boolean active;

    private Instant hiredAt;

    private Instant terminatedAt;

    private Instant updatedAt;

    public void checkValidRegistration() {
        requireNonNull(userId, () -> new BadRequestException("El id del empleado es requerido"));
        requireNonBlank(fullName, () -> new BadRequestException("El nombre del empleado es requerido"));
        requireNonBlank(email, () -> new BadRequestException("El correo del empleado es requerido"));
        requireNonBlank(role, () -> new BadRequestException("El rol del empleado es requerido"));
        requireNonNull(active, () -> new BadRequestException("El estado del empleado es requerido"));
    }

    public void updateFrom(User updated) {
        this.fullName = firstNonNull(updated.getFullName(), this.getFullName());
        this.email = firstNonNull(updated.getEmail(), this.getEmail());
        this.role = firstNonNull(updated.getRole(), this.getRole());
        this.active = firstNonNull(updated.getActive(), this.getActive());
        if (hiredAt == null && role.equals("USER") && !role.equals(updated.getRole())) {
            hiredAt = Instant.now();
        }
        if (terminatedAt == null && updated.getRole().equals("USER") && !role.equals(updated.getRole())) {
            terminatedAt = Instant.now();
        }
    }
}
