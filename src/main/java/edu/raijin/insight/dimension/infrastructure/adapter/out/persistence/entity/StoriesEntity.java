package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import edu.raijin.commons.domain.type.StoryPriority;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

@Entity(name = "stories")
@Table(name = "stories", schema = "dim")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "storyId")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class StoriesEntity {

    @Id
    private UUID storyId;

    @With
    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = true)
    private SprintsEntity sprint;

    private String name;

    private String stage;

    @Builder.Default
    private Integer points = 0;

    @Enumerated(STRING)
    private StoryPriority priority;

    @UpdateTimestamp
    private Instant updatedAt;
}
