package ru.itis.gitstats.dto.github;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommitDto {

	@JsonProperty("committer")
	private CommitterDto committer;

	@JsonProperty("author")
	private AuthorDto author;

	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("comments_url")
	private String commentsUrl;

	@JsonProperty("sha")
	private String sha;

	@JsonProperty("url")
	private String url;

	@JsonProperty("node_id")
	private String nodeId;

	@JsonProperty("commit")
	private CommitDetails commitDetails;

	@JsonProperty("parents")
	private List<ParentsItemDto> parents;
}