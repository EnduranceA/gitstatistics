package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itis.gitstats.dto.github.UserDto;

public class ReviewDto {

    @JsonProperty("author_association")
    private String authorAssociation;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("submitted_at")
    private String submittedAt;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("pull_request_url")
    private String pullRequestUrl;

    @JsonProperty("body")
    private String body;

    @JsonProperty("user")
    private UserDto user;

    @JsonProperty("commit_id")
    private String commitId;

    @JsonProperty("node_id")
    private String nodeId;
}