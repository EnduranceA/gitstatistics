package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itis.gitstats.dto.github.UserDto;

public class MilestoneDto {

    @JsonProperty("creator")
    private UserDto creator;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("title")
    private String title;

    @JsonProperty("closed_issues")
    private Integer closedIssues;

    @JsonProperty("url")
    private String url;

    @JsonProperty("due_on")
    private String dueOn;

    @JsonProperty("labels_url")
    private String labelsUrl;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("open_issues")
    private Integer openIssues;

    @JsonProperty("node_id")
    private String nodeId;
}