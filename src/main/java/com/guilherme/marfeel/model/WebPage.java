package com.guilherme.marfeel.model;

public class WebPage {
	private String url;
	private int rank;
	private String title;
	
	public WebPage(String url, int rank) {
		this.url = url;
		this.rank = rank;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
