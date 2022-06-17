package ru.itis.gitstats.dto.github.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeeksItemDto {

    //количество добавлений
    @JsonProperty("a")
    private Integer A;

    //количество коммитов
    @JsonProperty("c")
    private Integer C;

    //количество удалений
    @JsonProperty("d")
    private Integer D;

    //неделя
    @JsonProperty("w")
    private long W;
}