package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links {

    @JsonProperty("comments")
    private CommentsDto comments;

    @JsonProperty("issue")
    private IssueDto issue;

    @JsonProperty("self")
    private SelfDto self;

    @JsonProperty("review_comments")
    private ReviewCommentsDto reviewComments;

    @JsonProperty("commits")
    private CommitsDto commits;

    @JsonProperty("statuses")
    private StatusesDto statuses;

    @JsonProperty("html")
    private HtmlDto html;

    @JsonProperty("review_comment")
    private ReviewCommentDto reviewComment;
}