package ru.itis.gitstats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.gitstats.dto.github.statistics.WeeksItemDto;

@Data
@AllArgsConstructor
@Builder
public class DeveloperProductivityDto {

    private String author;

    private String date;

    private WeeksItemDto weeksItem;
}
