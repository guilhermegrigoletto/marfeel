package com.guilherme.marfeel.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AnalyzedWebsite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String url;
	private boolean marfeelizable;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateAnalyzed;
	
	public AnalyzedWebsite() {
	}
	
	public AnalyzedWebsite(String url, boolean marfeelizable) {
		this.url = url;
		this.marfeelizable = marfeelizable;
		this.dateAnalyzed = Calendar.getInstance();
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isMarfeelizable() {
		return marfeelizable;
	}
	public void setMarfeelizable(boolean marfeelizable) {
		this.marfeelizable = marfeelizable;
	}
	public Calendar getDateAnalyzed() {
		return dateAnalyzed;
	}
	public void setDateAnalyzed(Calendar dateAnalyzed) {
		this.dateAnalyzed = dateAnalyzed;
	}
	
	
	
}
