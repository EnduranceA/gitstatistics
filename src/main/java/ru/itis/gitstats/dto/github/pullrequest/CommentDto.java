package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.itis.gitstats.dto.github.UserDto;

@Data
public class CommentDto {

    @JsonProperty("original_commit_id")
    private String originalCommitId;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("in_reply_to_id")
    private Integer inReplyToId;

    @JsonProperty("diff_hunk")
    private String diffHunk;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("pull_request_url")
    private String pullRequestUrl;

    @JsonProperty("body")
    private String body;

    @JsonProperty("url")
    private String url;

    @JsonProperty("author_association")
    private String authorAssociation;

    @JsonProperty("path")
    private String path;

    @JsonProperty("original_position")
    private Integer originalPosition;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("pull_request_review_id")
    private Integer pullRequestReviewId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("commit_id")
    private String commitId;

    @JsonProperty("user")
    private UserDto user;

    @JsonProperty("node_id")
    private String nodeId;
}