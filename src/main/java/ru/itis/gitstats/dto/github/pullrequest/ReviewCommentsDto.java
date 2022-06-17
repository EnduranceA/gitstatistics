package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewCommentsDto {

    @JsonProperty("href")
    private String href;
}