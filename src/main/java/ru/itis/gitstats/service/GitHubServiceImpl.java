package ru.itis.gitstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import ru.itis.gitstats.feign.GitHubClient;
import ru.itis.gitstats.mappers.GitHubMapper;
import ru.itis.gitstats.models.Repo;
import ru.itis.gitstats.models.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@Component
public class GitHubServiceImpl implements GitHubService {

    @Autowired
    private GitHubClient gitHubClient;

    @Autowired
    private StorageService storageService;

    @Autowired
    private GitHubMapper mapper;

    @Autowired
    private ExecutorService executor;

    // Получение пользователя. Гарантируется, что пользователь сохранен в бд (имеет id)
    public UserDto getUserByUsername(String username) {
        Optional<User> fromStorage = storageService.findUserByUserName(username);
        User user;
        if (fromStorage.isPresent()) {
            user = fromStorage.get();
        } else {
            UserDto fromApi = gitHubClient.findUser(username);
            user = storageService.saveToStorage(fromApi);
        }
        return mapper.userModelToDto(user);
    }

    @Override
    public void handleWebhook(GitHubWebhookForm form) {
        Repo updatedRepo = findRepoModel(form.getRepo().getOwner().getLogin(), form.getRepo().getName());
        storageService.updateRepo(updatedRepo);
    }

    //РАБОТА С РЕПОЗИТОРИЯМИ
    @Override
    public List<RepoDto> findReposByUsername(String username) {
        Optional<List<Repo>> formStorage = storageService.findReposByUsername(username);
        if (formStorage.isPresent()) {
            return mapper.repoModelsToDtos(formStorage.get());
        } else {
            List<RepoDto> fromApi = gitHubClient.findReposByUsername(username);
            executor.submit(() -> storageService.updateReposByUsername(username, fromApi));
            return fromApi;
        }
    }

    @Override
    public RepoDto findRepo(String owner, String repo) {
        return mapper.repoModelToDto(findRepoModel(owner, repo));
    }

    @Override
    public Repo findRepoModel(String owner, String repo) {
        Optional<Repo> fromStorage = storageService.findRepoByOwnerAndName(owner, repo);
        Repo repository;
        if (fromStorage.isPresent()) {
            repository = fromStorage.get();
        } else {
            RepoDto fromApi = gitHubClient.findRepo(owner, repo);
            repository = storageService.saveRepoByUsername(owner, fromApi);
        }
        return repository;
    }

    @Override
    public List<UserDto> findRepoContributors(String owner, String repo) {
        Optional<List<User>> fromStorage = storageService.findRepoContributors(owner, repo);
        List<User> contributors;
        if (fromStorage.isPresent()) {
            contributors = fromStorage.get();
        } else {
            List<UserDto> fromApi = gitHubClient.findRepoContributors(owner, repo);
            contributors = storageService.saveRepoContributors(owner, repo, fromApi);
        }
        return mapper.userModelsToDto(contributors);
    }
    //РАБОТА С КОММИТАМИ

    @Override
    public List<CommitDto> findRepoCommits(String owner, String repo) {
        return gitHubClient.findRepoCommits(owner, repo);
    }

    @Override
    public CommitDto findFirstCommit(String owner, String repo) {
        return findRepoCommits(owner, repo).get(0);
    }

    @Override
    public CommitDto findLastCommit(String owner, String repo) {
        List<CommitDto> commits = findRepoCommits(owner, repo);
        return commits.get(commits.size() - 1);
    }
    //ПОПУЛЯРНОСТЬ РЕПОЗИТОРИЯ И ТРАФИК

    @Override
    public List<UserDto> findRepoStargazers(String owner, String repo) {
        return gitHubClient.findRepoStargazers(owner, repo);
    }

    @Override
    public List<UserDto> findRepoSubscribers(String owner, String repo) {
        return gitHubClient.findRepoSubscribers(owner, repo);
    }

    @Override
    public RepositoryClonesDto findRepoClones(String owner, String repo) {
        return gitHubClient.findRepoClones(owner, repo);
    }

    @Override
    public List<ReferralPathDto> findTopReferralPaths(String owner, String repo) {
        return gitHubClient.findTopReferralPaths(owner, repo);
    }

    @Override
    public PageViewDto findRepoViews(String owner, String repo) {
        return gitHubClient.findRepoViews(owner, repo);
    }

    // РАБОТА С ВЕТКАМИ

    @Override
    public List<BranchDto> findRepoBranches(String owner, String repo) {
        return gitHubClient.findRepoBranches(owner, repo);
    }
    // РАБОТА СО СТАТИСТИКОй

    @Override
    public List<Integer> findWeeklyCommitActivity(String owner, String repo) {
        return gitHubClient.findWeeklyCommitActivity(owner, repo);
    }

    @Override
    public List<LastYearCommitActivityDto> findLastYearCommitActivity(String owner, String repo) {
        return gitHubClient.findLastYearCommitActivity(owner, repo);
    }

    @Override
    public List<ContributorCommitActivityDto> findContributorsCommitActivity(String owner, String repo) {
        return gitHubClient.findContributorsCommitActivity(owner, repo);
    }

    @Override
    public WeeklyCommitCountDto findWeeklyCommitCount(String owner, String repo) {
        return gitHubClient.findWeeklyCommitCount(owner, repo);
    }

    @Override
    public List<List<Integer>> findHourlyCommitCount(String owner, String repo) {
        return gitHubClient.findHourlyCommitCount(owner, repo);
    }

    //PULL REQUEST
    @Override
    public List<PullRequestDto> findPullRequests(String owner, String repo, String state) {
        return gitHubClient.findPullRequests(owner, repo, state);
    }

    @Override
    public List<ReviewDto> findReviewsOfPullRequest(String owner, String repo, Integer pullNumber) {
        return gitHubClient.findReviewsOfPullRequest(owner, repo, pullNumber);
    }

    @Override
    public List<CommitDto> findCommitsOnPullRequest(String owner, String repo, Integer pullNumber) {
        return gitHubClient.findCommitsOnPullRequest(owner, repo, pullNumber);
    }

    @Override
    public List<CommentDto> findCommentsOnPullRequestReview(String owner, String repo, Integer pullNumber, Integer reviewId) {
        return gitHubClient.findCommentsOnPullRequestReview(owner, repo, pullNumber, reviewId);
    }

    @Override
    public List<CommentDto> findCommentsOnPullRequests(String owner, String repo) {
        return gitHubClient.findCommentsOnPullRequests(owner, repo);
    }


}
