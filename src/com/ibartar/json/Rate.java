package com.ibartar.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Rate {
	

	@JsonProperty("credibility")
	private String credibility;
	
	@JsonProperty("count")
	private String count;
	
	@JsonProperty("reliability")
	private String reliability;
	
	@JsonProperty("popularity")
	private String popularity;
	

}
