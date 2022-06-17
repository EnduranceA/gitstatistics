package ru.itis.gitstats.dto.github;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gitstats.dto.DeveloperProductivityDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityShortDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CharacteristicsDto {

    private List<ContributorCommitActivityShortDto> contributorsActivity;
    private Long averageTimeEstimationPR;
    private Set<String> importantPackages;
    private Set<String> packagesProneToBreakage;
    private Set<String> stablePackages;
    private Set<String> unstablePackages;
    private Set<String> balancedPackages;
    private String teamAtmosphere;
    private String developerTurnoverPerYear;
    private List<DeveloperProductivityDto> developersPerformPoorly;
    private List<ContributorCommitActivityShortDto> developersRecycles;
}
