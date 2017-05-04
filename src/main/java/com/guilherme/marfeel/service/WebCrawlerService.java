package com.guilherme.marfeel.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.marfeel.business.BasicMarfeelizable;
import com.guilherme.marfeel.business.TitleMarfeelizable;
import com.guilherme.marfeel.dao.AnalyzedWebsiteDAO;
import com.guilherme.marfeel.model.AnalyzedWebsite;
import com.guilherme.marfeel.model.WebPage;
import com.guilherme.marfeel.rest.URLRepresentation;

@Service
public class WebCrawlerService {
	private static final int NUMBER_THREADS = 10;
	private static final String HTTP_PATTERN = "^\\w+://.*";
	private final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);
	
	private ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_THREADS);
	private List<Future<WebPage>> futures = new ArrayList<>();
	private List<AnalyzedWebsite> analyzedWebsites = new ArrayList<>();
	
	@Autowired
	private AnalyzedWebsiteDAO dao;
	
	public void crawlPages(List<URLRepresentation> addresses)  {
		if(addresses == null || addresses.isEmpty())
			return;
		
		for(URLRepresentation address : addresses) {
			WebPage page = convertToWebPage(address);
			Future<WebPage> future = executorService.submit(new PageCrawler(page));
			futures.add(future);
		}
		
		while(checkFutureResults());
		
		saveAnalyzedWebsites();
		
		analyzedWebsites = new ArrayList<>();
	}
	
	private WebPage convertToWebPage(URLRepresentation address) {
		WebPage page = new WebPage(appendHTTPIfNecessary(address.getUrl()), address.getRank());
		return page;
	}

	private void saveAnalyzedWebsites() {
		for(AnalyzedWebsite analyzedWebsite : analyzedWebsites) {
			dao.save(analyzedWebsite);
		}
	}

	private boolean checkFutureResults() {
		Iterator<Future<WebPage>> iterator = futures.iterator();

		while (iterator.hasNext()) {
			Future<WebPage> future = iterator.next();
			if (future.isDone()) {
				iterator.remove();
				processPage(future);
			}
		}

		return (futures.size() > 0);
	}

	private void processPage(Future<WebPage> future)  {
		try {
			WebPage webPage = future.get();
			boolean marfeelizable = new TitleMarfeelizable(new BasicMarfeelizable()).isMarfeelizable(webPage);
			AnalyzedWebsite analyzed = new AnalyzedWebsite(webPage.getUrl(), marfeelizable);
			this.analyzedWebsites.add(analyzed);
		} catch (ExecutionException e) {
			logger.error("computation error loading page", e);
		} catch (InterruptedException e) {
			logger.error("Page was too slow to load", e);
		}
	}
	
	private String appendHTTPIfNecessary(String url) {
		if (!url.toLowerCase().matches(HTTP_PATTERN)) 
		    url = "http://" + url;
		
		return url;
	}
}
