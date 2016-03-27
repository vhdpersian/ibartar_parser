package com.ibartar.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Uuid {


	@JsonProperty("uuid")
	private String uuid;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("tel_1")
	private String tel_1;
	
	@JsonProperty("lat")
	private String lat;
	
	@JsonProperty("lng")
	private String lng;
	
	@JsonProperty("slogan")
	private String slogan;
		
	@JsonProperty("guilds")
	private List<Guild>  guilds;
		
	@JsonProperty("logo")
	private Logo  logo;
		
	@JsonProperty("slugged_title")
	private String  slugged_title;
	
	@JsonProperty("distance")
	private String  distance;
	
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTel_1() {
		return tel_1;
	}

	public void setTel_1(String tel_1) {
		this.tel_1 = tel_1;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public List<Guild> getGuilds() {
		return guilds;
	}

	public void setGuilds(List<Guild> guilds) {
		this.guilds = guilds;
	}

	public Logo getLogo() {
		return logo;
	}

	public void setLogo(Logo logo) {
		this.logo = logo;
	}

	public String getSlugged_title() {
		return slugged_title;
	}

	public void setSlugged_title(String slugged_title) {
		this.slugged_title = slugged_title;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}


}
