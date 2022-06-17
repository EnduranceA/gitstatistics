package ru.itis.gitstats.service;

import ru.itis.gitstats.dto.github.*;
import ru.itis.gitstats.dto.github.pullrequest.CommentDto;
import ru.itis.gitstats.dto.github.pullrequest.PullRequestDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.dto.github.pullrequest.ReviewDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityDto;
import ru.itis.gitstats.dto.github.statistics.LastYearCommitActivityDto;
import ru.itis.gitstats.dto.github.statistics.WeeklyCommitCountDto;
import ru.itis.gitstats.dto.github.traffic.PageViewDto;
import ru.itis.gitstats.dto.github.traffic.ReferralPathDto;
import ru.itis.gitstats.dto.github.traffic.RepositoryClonesDto;
import ru.itis.gitstats.models.Repo;

import java.util.List;

public interface GitHubService {
    List<RepoDto> findReposByUsername(String username);

    RepoDto findRepo(String owner, String repo);

    Repo findRepoModel(String owner, String repo);

    List<UserDto> findRepoContributors(String owner, String repo);

    List<CommitDto> findRepoCommits(String owner, String repo);

    CommitDto findFirstCommit(String owner, String repo);

    CommitDto findLastCommit(String owner, String repo);

    List<UserDto> findRepoStargazers(String owner, String repo);

    List<UserDto> findRepoSubscribers(String owner, String repo);

    RepositoryClonesDto findRepoClones(String owner, String repo);

    List<ReferralPathDto> findTopReferralPaths(String owner, String repo);

    PageViewDto findRepoViews(String owner, String repo);

    List<BranchDto> findRepoBranches(String owner, String repo);

    List<Integer> findWeeklyCommitActivity(String owner, String repo);

    List<LastYearCommitActivityDto> findLastYearCommitActivity(String owner, String repo);

    List<ContributorCommitActivityDto> findContributorsCommitActivity(String owner, String repo);

    WeeklyCommitCountDto findWeeklyCommitCount(String owner, String repo);

    List<List<Integer>> findHourlyCommitCount(String owner, String repo);

    List<PullRequestDto> findPullRequests(String owner, String repo, String state);

    List<ReviewDto> findReviewsOfPullRequest(String owner, String repo, Integer pullNumber);

    List<CommitDto> findCommitsOnPullRequest(String owner, String repo, Integer pullNumber);

    List<CommentDto> findCommentsOnPullRequestReview(String owner, String repo, Integer pullNumber, Integer reviewId);

    List<CommentDto> findCommentsOnPullRequests(String owner, String repo);

    UserDto getUserByUsername(String username);

    void handleWebhook(GitHubWebhookForm form);
}
