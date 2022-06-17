package ru.itis.gitstats.dto.github.pullrequest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.itis.gitstats.dto.github.UserDto;

@Data
public class PullRequestDto {

    @JsonProperty("issue_url")
    private String issueUrl;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("diff_url")
    private String diffUrl;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("assignees")
    private List<AssigneesItemDto> assignees;

    @JsonProperty("requested_reviewers")
    private List<UserDto> requestedReviewers;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    @JsonProperty("requested_teams")
    private List<RequestedTeamsItemDto> requestedTeams;

    @JsonProperty("head")
    private HeadDto head;

    @JsonProperty("author_association")
    private String authorAssociation;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("patch_url")
    private String patchUrl;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("draft")
    private Boolean draft;

    @JsonProperty("comments_url")
    private String commentsUrl;

    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;

    @JsonProperty("review_comment_url")
    private String reviewCommentUrl;

    @JsonProperty("active_lock_reason")
    private String activeLockReason;

    @JsonProperty("id")
    private long id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("commits_url")
    private String commitsUrl;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("statuses_url")
    private String statusesUrl;

    @JsonProperty("merged_at")
    private String mergedAt;

    @JsonProperty("auto_merge")
    private Object autoMerge;

    @JsonProperty("url")
    private String url;

    @JsonProperty("labels")
    private List<LabelsItemDto> labels;

    @JsonProperty("milestone")
    private MilestoneDto milestone;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("review_comments_url")
    private String reviewCommentsUrl;

    @JsonProperty("assignee")
    private AssigneeDto assignee;

    @JsonProperty("user")
    private UserDto user;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("base")
    private BaseDto base;
}