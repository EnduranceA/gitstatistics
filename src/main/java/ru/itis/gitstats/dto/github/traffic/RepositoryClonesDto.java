package ru.itis.gitstats.dto.github.traffic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryClonesDto{

	@JsonProperty("clones")
	private List<ClonesItemDto> clones;

	@JsonProperty("count")
	private Integer count;

	@JsonProperty("uniques")
	private Integer uniques;
}