package ru.itis.gitstats.dto.github.pullrequest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.itis.gitstats.dto.github.UserDto;

@Data
public class RepoDto {

    @JsonProperty("stargazers_count")
    private Integer stargazersCount;

    @JsonProperty("is_template")
    private Boolean isTemplate;

    @JsonProperty("pushed_at")
    private String pushedAt;

    @JsonProperty("subscription_url")
    private String subscriptionUrl;

    @JsonProperty("language")
    private Object language;

    @JsonProperty("branches_url")
    private String branchesUrl;

    @JsonProperty("issue_comment_url")
    private String issueCommentUrl;

    @JsonProperty("allow_rebase_merge")
    private Boolean allowRebaseMerge;

    @JsonProperty("labels_url")
    private String labelsUrl;

    @JsonProperty("subscribers_url")
    private String subscribersUrl;

    @JsonProperty("permissions")
    private PermissionsDto permissions;

    @JsonProperty("temp_clone_token")
    private String tempCloneToken;

    @JsonProperty("releases_url")
    private String releasesUrl;

    @JsonProperty("svn_url")
    private String svnUrl;

    @JsonProperty("subscribers_count")
    private Integer subscribersCount;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("forks")
    private Integer forks;

    @JsonProperty("archive_url")
    private String archiveUrl;

    @JsonProperty("allow_merge_commit")
    private Boolean allowMergeCommit;

    @JsonProperty("git_refs_url")
    private String gitRefsUrl;

    @JsonProperty("forks_url")
    private String forksUrl;

    @JsonProperty("visibility")
    private String visibility;

    @JsonProperty("statuses_url")
    private String statusesUrl;

    @JsonProperty("network_count")
    private Integer networkCount;

    @JsonProperty("ssh_url")
    private String sshUrl;

    @JsonProperty("license")
    private LicenseDto license;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("template_repository")
    private Object templateRepository;

    @JsonProperty("allow_auto_merge")
    private Boolean allowAutoMerge;

    @JsonProperty("languages_url")
    private String languagesUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pulls_url")
    private String pullsUrl;

    @JsonProperty("default_branch")
    private String defaultBranch;

    @JsonProperty("hooks_url")
    private String hooksUrl;

    @JsonProperty("trees_url")
    private String treesUrl;

    @JsonProperty("tags_url")
    private String tagsUrl;

    @JsonProperty("private")
    private Boolean jsonMemberPrivate;

    @JsonProperty("contributors_url")
    private String contributorsUrl;

    @JsonProperty("has_downloads")
    private Boolean hasDownloads;

    @JsonProperty("notifications_url")
    private String notificationsUrl;

    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("watchers")
    private Integer watchers;

    @JsonProperty("deployments_url")
    private String deploymentsUrl;

    @JsonProperty("keys_url")
    private String keysUrl;

    @JsonProperty("has_projects")
    private Boolean hasProjects;

    @JsonProperty("archived")
    private Boolean archived;

    @JsonProperty("has_wiki")
    private Boolean hasWiki;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("comments_url")
    private String commentsUrl;

    @JsonProperty("stargazers_url")
    private String stargazersUrl;

    @JsonProperty("disabled")
    private Boolean disabled;

    @JsonProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;

    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("has_pages")
    private Boolean hasPages;

    @JsonProperty("owner")
    private UserDto owner;

    @JsonProperty("allow_squash_merge")
    private Boolean allowSquashMerge;

    @JsonProperty("commits_url")
    private String commitsUrl;

    @JsonProperty("compare_url")
    private String compareUrl;

    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;

    @JsonProperty("topics")
    private List<String> topics;

    @JsonProperty("blobs_url")
    private String blobsUrl;

    @JsonProperty("git_tags_url")
    private String gitTagsUrl;

    @JsonProperty("merges_url")
    private String mergesUrl;

    @JsonProperty("downloads_url")
    private String downloadsUrl;

    @JsonProperty("has_issues")
    private Boolean hasIssues;

    @JsonProperty("url")
    private String url;

    @JsonProperty("contents_url")
    private String contentsUrl;

    @JsonProperty("mirror_url")
    private String mirrorUrl;

    @JsonProperty("milestones_url")
    private String milestonesUrl;

    @JsonProperty("teams_url")
    private String teamsUrl;

    @JsonProperty("fork")
    private Boolean fork;

    @JsonProperty("issues_url")
    private String issuesUrl;

    @JsonProperty("events_url")
    private String eventsUrl;

    @JsonProperty("issue_events_url")
    private String issueEventsUrl;

    @JsonProperty("assignees_url")
    private String assigneesUrl;

    @JsonProperty("open_issues")
    private Integer openIssues;

    @JsonProperty("watchers_count")
    private Integer watchersCount;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("forks_count")
    private Integer forksCount;
}