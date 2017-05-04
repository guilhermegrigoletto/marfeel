package com.guilherme.marfeel.business;

import com.guilherme.marfeel.model.WebPage;

public class BasicMarfeelizable implements Marfeelizable {

	@Override
	public boolean isMarfeelizable(WebPage page) {
		return page != null;
	}

}
