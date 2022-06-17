package ru.itis.gitstats.dto.github.traffic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViewsItemDto {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("uniques")
    private Integer uniques;

    @JsonProperty("timestamp")
    private String timestamp;

}