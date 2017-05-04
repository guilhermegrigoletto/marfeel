package com.guilherme.marfeel.business;

import com.guilherme.marfeel.model.WebPage;

/**
 *	This is a decorator, it may add behavior to the object. 
 *	Adding flexibility when new rules come up to know what is marfeelizable or not  
 */
public class MarfeelizableDecorator implements Marfeelizable {

	private Marfeelizable marfeelizable;
	
	public MarfeelizableDecorator(Marfeelizable m) {
		this.marfeelizable = m;
	}

	@Override
	public boolean isMarfeelizable(WebPage page) {
		return this.marfeelizable.isMarfeelizable(page);
	}

}
