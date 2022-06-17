package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchDto {

    @JsonProperty("protected")
    private Boolean jsonMemberProtected;

    @JsonProperty("name")
    private String name;

    @JsonProperty("commit")
    private CommitDto commit;

    @JsonProperty("protection")
    private ProtectionDto protection;

    @JsonProperty("protection_url")
    private String protectionUrl;
}