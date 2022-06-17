package ru.itis.gitstats.dto.github.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContributorCommitActivityShortDto {

    private String name;

    //количество добавлений
    @JsonProperty("add")
    private Integer add;

    //количество коммитов
    @JsonProperty("commit")
    private Integer commit;

    //количество удалений
    @JsonProperty("delete")
    private Integer delete;
}