package ru.itis.gitstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.gitstats.dto.github.UserDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityDto;
import ru.itis.gitstats.service.GitHubService;

import java.util.List;

@RestController
public class GitStatsController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/users/{username}/repos")
    public ResponseEntity<List<RepoDto>> getUserRepos(@PathVariable String username) {
        return ResponseEntity.ok(gitHubService.findReposByUsername(username));
    }

    @GetMapping("/repos/{owner}/{repo}/stats/contributors/activity")
    public ResponseEntity<ContributorCommitActivityDto> getListActivity(@PathVariable String owner, @PathVariable String repo) {
        List<ContributorCommitActivityDto> result = gitHubService.findContributorsCommitActivity(owner, repo);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result.get(0));
        }
    }

    @GetMapping("/repos/{owner}/{repo}/contributors")
    public ResponseEntity<List<UserDto>> getRepoContributors(@PathVariable String owner, @PathVariable String repo) {
        return ResponseEntity.ok(gitHubService.findRepoContributors(owner, repo));
    }
}
