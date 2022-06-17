package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationDto {

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("signature")
    private Object signature;

    @JsonProperty("payload")
    private Object payload;

    @JsonProperty("verified")
    private Boolean verified;
}