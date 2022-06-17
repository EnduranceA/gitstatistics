package ru.itis.gitstats.dto.github.statistics;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.itis.gitstats.dto.github.AuthorDto;

@Data
public class ContributorCommitActivityDto {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("weeks")
    private List<WeeksItemDto> weeks;

    @JsonProperty("author")
    private AuthorDto author;
}