package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.type.SqlTypes.JSON;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity(name = "snapshots")
@Table(name = "snapshots", schema = "audit")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class SnapshotsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private UUID aggregateId;

    @With
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = true)
    private UsersEntity actor;

    private Instant occurredAt;

    private String description;

    @JdbcTypeCode(JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> details;
}
