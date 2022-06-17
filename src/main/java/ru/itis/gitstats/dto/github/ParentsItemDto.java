package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentsItemDto {

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("url")
    private String url;
}