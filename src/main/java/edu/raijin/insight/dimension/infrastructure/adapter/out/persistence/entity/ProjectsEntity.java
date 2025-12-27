package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "projects")
@Table(name = "projects", schema = "dim")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "projectId")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class ProjectsEntity {

    @Id
    private UUID projectId;

    private String name;

    private String client;

    private Boolean closed;

    @CurrentTimestamp
    private Instant startDate;

    private Instant endDate;

    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "members", schema = "bridge", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UsersEntity> members;
}
