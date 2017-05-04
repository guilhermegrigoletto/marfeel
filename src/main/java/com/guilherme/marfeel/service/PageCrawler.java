package com.guilherme.marfeel.service;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guilherme.marfeel.model.WebPage;

/**
 *	This class uses jSoup to visit the website informed, crawling its HTML
 */
public class PageCrawler implements Callable<WebPage> {
	
	private static final int TIMEOUT = 30000;   //30 seconds
	private final Logger logger = LoggerFactory.getLogger(PageCrawler.class);
	
	private WebPage webPage;
	
	public PageCrawler(WebPage webPage) {
		this.webPage = webPage;
	}

	@Override
	public WebPage call() {
		try {
			Document doc = Jsoup.connect(this.webPage.getUrl())
					.timeout(TIMEOUT)
					.get();
			this.webPage.setTitle(doc.title());
			
		} catch (IOException e) {
			logger.error("Error connecting to page: " + this.webPage.getTitle(), e);
		}

		return this.webPage;
	}
}
