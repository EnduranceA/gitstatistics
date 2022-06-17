package ru.itis.gitstats.dto.github;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequiredStatusChecksDto {

    @JsonProperty("enforcement_level")
    private String enforcementLevel;

    @JsonProperty("contexts")
    private List<String> contexts;
}