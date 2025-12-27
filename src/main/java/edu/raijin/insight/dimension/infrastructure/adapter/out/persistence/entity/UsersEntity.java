package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity;

import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users", schema = "dim")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "userId")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class UsersEntity {

    @Id
    private UUID userId;

    private String fullName;

    private String email;

    private String role;

    private Boolean active;

    private Instant hiredAt;

    private Instant terminatedAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
