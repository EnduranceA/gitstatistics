package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
// Не используется. Почти то же самое что и UserDto
public class ContributorDto {

    @JsonProperty("gists_url")
    private String gistsUrl;

    @JsonProperty("repos_url")
    private String reposUrl;

    @JsonProperty("following_url")
    private String followingUrl;

    @JsonProperty("starred_url")
    private String starredUrl;

    @JsonProperty("login")
    private String login;

    @JsonProperty("followers_url")
    private String followersUrl;

    @JsonProperty("type")
    private String type;

    @JsonProperty("url")
    private String url;

    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;

    @JsonProperty("received_events_url")
    private String receivedEventsUrl;

    @JsonProperty("contributions")
    private Integer contributions;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("events_url")
    private String eventsUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("site_admin")
    private Boolean siteAdmin;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("gravatar_id")
    private String gravatarId;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("organizations_url")
    private String organizationsUrl;
}