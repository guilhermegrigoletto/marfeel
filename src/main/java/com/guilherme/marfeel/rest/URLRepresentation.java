package com.guilherme.marfeel.rest;

public class URLRepresentation {
	private String url;
	private Integer rank;
	
	public URLRepresentation() {
	}
	
	public URLRepresentation(String url, Integer rank) {
		this.url = url;
		this.rank = rank;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	
	
}
