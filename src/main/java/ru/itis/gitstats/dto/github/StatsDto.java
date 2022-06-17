package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDto {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("additions")
    private Integer additions;

    @JsonProperty("deletions")
    private Integer deletions;
}