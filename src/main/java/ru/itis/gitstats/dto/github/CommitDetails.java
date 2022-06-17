package ru.itis.gitstats.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommitDetails {

	@JsonProperty("comment_count")
	private Integer commentCount;

	@JsonProperty("committer")
	private CommitterDto committer;

	@JsonProperty("author")
	private AuthorDto author;

	@JsonProperty("tree")
	private TreeDto tree;

	@JsonProperty("message")
	private String message;

	@JsonProperty("url")
	private String url;

	@JsonProperty("verification")
	private VerificationDto verification;
}