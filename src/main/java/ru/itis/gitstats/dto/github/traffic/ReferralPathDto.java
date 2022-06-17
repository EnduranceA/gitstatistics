package ru.itis.gitstats.dto.github.traffic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferralPathDto {

    @JsonProperty("path")
    private String path;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("uniques")
    private Integer uniques;

    @JsonProperty("title")
    private String title;
}