package com.guilherme.marfeel.business;

import com.guilherme.marfeel.model.WebPage;

public class TitleMarfeelizable extends MarfeelizableDecorator {

	public TitleMarfeelizable(Marfeelizable m) {
		super(m);
	}
	
	@Override
	public boolean isMarfeelizable(WebPage page) {
		String title = page.getTitle();
		
		if(title == null || title.isEmpty())
			return false;
		
		return title.toLowerCase().contains("news") || title.toLowerCase().contains("noticias");
	}
	
}
