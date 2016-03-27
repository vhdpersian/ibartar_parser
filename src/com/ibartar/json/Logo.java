package com.ibartar.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Logo {

	@JsonProperty("FullSize")
	private String FullSize;
	
	@JsonProperty("Small")
	private String Small;

	public String getFullSize() {
		return FullSize;
	}

	public void setFullSize(String fullSize) {
		FullSize = fullSize;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getSmall() {
		return Small;
	}

	public void setSmall(String small) {
		Small = small;
	}
			
}
