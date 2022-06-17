package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestedTeamsItemDto {

    @JsonProperty("parent")
    private Object parent;

    @JsonProperty("repositories_url")
    private String repositoriesUrl;

    @JsonProperty("members_url")
    private String membersUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("privacy")
    private String privacy;

    @JsonProperty("permission")
    private String permission;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("node_id")
    private String nodeId;
}