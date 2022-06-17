package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelsItemDto {

    @JsonProperty("default")
    private Boolean jsonMemberDefault;

    @JsonProperty("color")
    private String color;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    private long id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("node_id")
    private String nodeId;
}