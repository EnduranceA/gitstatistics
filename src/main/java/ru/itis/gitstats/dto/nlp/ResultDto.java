package ru.itis.gitstats.dto.nlp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultDto {

    @JsonProperty("neutral")
    public float neutralTy;

    @JsonProperty("positive")
    public float type;


}
