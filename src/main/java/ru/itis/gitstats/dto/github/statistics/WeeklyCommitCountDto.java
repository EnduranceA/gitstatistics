package ru.itis.gitstats.dto.github.statistics;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeeklyCommitCountDto {

    @JsonProperty("all")
    private List<Integer> all;

    @JsonProperty("owner")
    private List<Integer> owner;
}