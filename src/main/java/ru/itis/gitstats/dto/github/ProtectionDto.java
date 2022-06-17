package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProtectionDto {

    @JsonProperty("required_status_checks")
    private RequiredStatusChecksDto requiredStatusChecks;
}