package ru.itis.gitstats.dto.github.traffic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClonesItemDto {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("uniques")
    private Integer uniques;

    @JsonProperty("timestamp")
    private String timestamp;
}