package ru.itis.gitstats.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

import java.util.List;

@FeignClient(name = "git-hub-client",url = "https://api.github.com")
public interface GitHubClient {
    @GetMapping("/users/{username}")
    UserDto findUser(@PathVariable String username);

    @GetMapping("/users/{username}/repos")
    List<RepoDto> findReposByUsername(@PathVariable String username);

    @GetMapping("/repos/{owner}/{repo}")
    RepoDto findRepo(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/commits")
    List<CommitDto> findRepoCommits(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/stargazers")
    List<UserDto> findRepoStargazers(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/subscribers")
    List<UserDto> findRepoSubscribers(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/contributors")
    List<UserDto> findRepoContributors(@PathVariable String owner, @PathVariable String repo);

    //Получение общего количества клонов (разбивка по дням или неделям за последние 14 дней)
    //Неделя начинается в понедельник.
    @GetMapping("/repos/{owner}/{repo}/traffic/clones")
    RepositoryClonesDto findRepoClones(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/traffic/popular/paths")
    List<ReferralPathDto> findTopReferralPaths(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/traffic/views")
    PageViewDto findRepoViews(@PathVariable String owner, @PathVariable String repo);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> findRepoBranches(@PathVariable String owner, @PathVariable String repo);

    //Возвращает еженедельную совокупность кол-ва добавленных и удаленных коммитов,
    //отправленных в репозиторий.
    @GetMapping("/repos/{owner}/{repo}/stats/code_frequency")
    List<Integer> findWeeklyCommitActivity(@PathVariable String owner, @PathVariable String repo);

    //Возвращает последний год активности коммитов, сгруппированный по неделям.
    //Массив days — это кол-во коммитов за день, начиная с воскресенья.
    @GetMapping("/repos/{owner}/{repo}/stats/commit_activity")
    List<LastYearCommitActivityDto> findLastYearCommitActivity(@PathVariable String owner, @PathVariable String repo);

    //Возвращает общее количество коммитов, созданных участником.
    //Кроме того, ответ включает недельный хеш (массив недель) со следующей информацией:
    //количество добавлений, удалений, количество комиитов
    @GetMapping("/repos/{owner}/{repo}/stats/contributors")
    List<ContributorCommitActivityDto> findContributorsCommitActivity(@PathVariable String owner, @PathVariable String repo);

    //Возвращает общее количество коммитов для owner и общее количество для всех.
    //all — это все вместе взятые, включая owner за последние 52 недели.
    //Для того, чтобы получить количество коммитов для тех, кто не является владельцем,
    //нужно вычесть владельца из всех.
    @GetMapping("/repos/{owner}/{repo}/stats/participation")
    WeeklyCommitCountDto findWeeklyCommitCount(@PathVariable String owner, @PathVariable String repo);

    //Каждый массив содержит номер дня, номер часа и количество коммитов:
    //0-6: воскресенье - суббота
    //0-23: час дня
    //Количество коммитов
    @GetMapping("/repos/{owner}/{repo}/stats/punch_card")
    List<List<Integer>> findHourlyCommitCount(@PathVariable String owner, @PathVariable String repo);

    //Получение всех pull requests
    @ResponseBody
    @GetMapping("/repos/{owner}/{repo}/pulls")
    List<PullRequestDto> findPullRequests(@PathVariable String owner, @PathVariable String repo,
                                          @RequestParam(required = false) String state);

    //Получение всех review, оставленных на pull request
    @GetMapping("/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    List<ReviewDto> findReviewsOfPullRequest(@PathVariable String owner, @PathVariable String repo,
                                             @PathVariable Integer pull_number);

    //Получение коммитов конкретного pull request
    @GetMapping("/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    List<CommitDto> findCommitsOnPullRequest(@PathVariable String owner, @PathVariable String repo,
                                             @PathVariable Integer pull_number);

    //Получение комментариев на review pull request
    @GetMapping("/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")
    List<CommentDto> findCommentsOnPullRequestReview(@PathVariable String owner, @PathVariable String repo,
                                                     @PathVariable Integer pull_number, @PathVariable Integer review_id);

    @GetMapping("/repos/{owner}/{repo}/pulls/comments")
    List<CommentDto> findCommentsOnPullRequests(@PathVariable String owner, @PathVariable String repo);
}
