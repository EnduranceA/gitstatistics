package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.itis.gitstats.dto.github.UserDto;

public class HeadDto {

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("repo")
    private RepoDto repo;

    @JsonProperty("label")
    private String label;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("user")
    private UserDto user;
}