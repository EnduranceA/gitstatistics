package ru.itis.gitstats.dto.github.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseDto {

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("spdx_id")
    private String spdxId;

    @JsonProperty("key")
    private String key;

    @JsonProperty("url")
    private String url;

    @JsonProperty("node_id")
    private String nodeId;
}