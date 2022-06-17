package ru.itis.gitstats.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Repo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<User> contributors;

    private LocalDateTime lastUpdated;

    //    private PermissionsDto permissions;
    //    private Object language;
    //    private LicenseDto license;
    //    private Object templateRepository;
    //    private List<String> topics;

    private String name;
    private Integer stargazersCount;
    private Boolean isTemplate;
    private String pushedAt;
    private String subscriptionUrl;
    private String branchesUrl;
    private String issueCommentUrl;
    private Boolean allowRebaseMerge;
    private String labelsUrl;
    private String subscribersUrl;
    private String tempCloneToken;
    private String releasesUrl;
    private String svnUrl;
    private Integer subscribersCount;
    private Integer forks;
    private String archiveUrl;
    private Boolean allowMergeCommit;
    private String gitRefsUrl;
    private String forksUrl;
    private String visibility;
    private String statusesUrl;
    private Integer networkCount;
    private String sshUrl;
    private String fullName;
    private Integer size;
    private Boolean allowAutoMerge;
    private String languagesUrl;
    private String htmlUrl;
    private String collaboratorsUrl;
    private String cloneUrl;
    private String pullsUrl;
    private String defaultBranch;
    private String hooksUrl;
    private String treesUrl;
    private String tagsUrl;
    private Boolean jsonMemberPrivate;
    private String contributorsUrl;
    private Boolean hasDownloads;
    private String notificationsUrl;
    private Integer openIssuesCount;
    private String description;
    private String createdAt;
    private Integer watchers;
    private String deploymentsUrl;
    private String keysUrl;
    private Boolean hasProjects;
    private Boolean archived;
    private Boolean hasWiki;
    private String updatedAt;
    private String commentsUrl;
    private String stargazersUrl;
    private Boolean disabled;
    private Boolean deleteBranchOnMerge;
    private String gitUrl;
    private Boolean hasPages;
    private Boolean allowSquashMerge;
    private String commitsUrl;
    private String compareUrl;
    private String gitCommitsUrl;
    private String blobsUrl;
    private String gitTagsUrl;
    private String mergesUrl;
    private String downloadsUrl;
    private Boolean hasIssues;
    private String url;
    private String contentsUrl;
    private String mirrorUrl;
    private String milestonesUrl;
    private String teamsUrl;
    private Boolean fork;
    private String issuesUrl;
    private String eventsUrl;
    private String issueEventsUrl;
    private String assigneesUrl;
    private Integer openIssues;
    private Integer watchersCount;
    private String nodeId;
    private String homepage;
    private Integer forksCount;

    @PrePersist
    public void prePersist() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Repo repo = (Repo) o;
        return id != null && Objects.equals(id, repo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
