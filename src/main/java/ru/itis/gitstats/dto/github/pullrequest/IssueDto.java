package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueDto {

    @JsonProperty("href")
    private String href;
}