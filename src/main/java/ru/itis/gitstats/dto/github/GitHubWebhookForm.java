package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;

@Data
public class GitHubWebhookForm {

    @JsonProperty("action")
    private String action;

    @JsonProperty("repository")
    private RepoDto repo;
}
