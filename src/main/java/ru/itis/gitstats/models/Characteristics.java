package ru.itis.gitstats.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.itis.gitstats.dto.DeveloperProductivityDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityShortDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Repo repo;

    private LocalDateTime lastUpdated;

    private Long averageTimeEstimationPR;
    private String teamAtmosphere;
    private String developerTurnoverPerYear;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<String> importantPackages;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<String> packagesProneToBreakage;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<String> stablePackages;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<String> unstablePackages;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Set<String> balancedPackages;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ContributorCommitActivityShortDto> contributorsActivity;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<DeveloperProductivityDto> developersPerformPoorly;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ContributorCommitActivityShortDto> developersRecycles;

    @PrePersist
    public void prePersist() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
