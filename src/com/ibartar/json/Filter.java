package com.ibartar.json;

import org.codehaus.jackson.annotate.JsonProperty;

public class Filter {
	

	@JsonProperty("key")
	private String key;
	
	@JsonProperty("doc_count")
	private String doc_count;
	
	@JsonProperty("title")
	private String title;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDoc_count() {
		return doc_count;
	}

	public void setDoc_count(String doc_count) {
		this.doc_count = doc_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}
