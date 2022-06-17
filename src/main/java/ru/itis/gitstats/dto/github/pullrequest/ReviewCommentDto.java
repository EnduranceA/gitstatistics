package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewCommentDto {

    @JsonProperty("href")
    private String href;
}