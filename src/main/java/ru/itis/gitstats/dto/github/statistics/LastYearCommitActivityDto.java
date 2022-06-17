package ru.itis.gitstats.dto.github.statistics;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LastYearCommitActivityDto {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("week")
    private Integer week;

    @JsonProperty("days")
    private List<Integer> days;
}