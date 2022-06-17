package ru.itis.gitstats.dto.github.traffic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageViewDto {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("uniques")
    private Integer uniques;

    @JsonProperty("views")
    private List<ViewsItemDto> views;

}