package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionsDto {

    @JsonProperty("pull")
    private Boolean pull;

    @JsonProperty("admin")
    private Boolean admin;

    @JsonProperty("push")
    private Boolean push;
}