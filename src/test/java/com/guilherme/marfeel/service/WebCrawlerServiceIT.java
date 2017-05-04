package com.guilherme.marfeel.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.marfeel.configuration.AppInitializer;
import com.guilherme.marfeel.configuration.SpringJPAConfiguration;
import com.guilherme.marfeel.configuration.WebConfig;
import com.guilherme.marfeel.rest.URLRepresentation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppInitializer.class,
		SpringJPAConfiguration.class, WebConfig.class})
@Transactional
public class WebCrawlerServiceIT {
	
	@Autowired
	private WebCrawlerService service;
	
	private List<URLRepresentation> urls = new ArrayList<>();
	
	
	@Before
	public void setUp(){
		urls = new ArrayList<>();
	}
	
	@Test
	public void testServiceIsUp(){
		service.crawlPages(urls);
	}
	
	@Test
	public void testPagesThatAreMarfeelizable(){
		urls.add(new URLRepresentation("elperiodico.com/es/barcelona/",123));
		urls.add(new URLRepresentation("bbc.com/news/uk/",1234));
		
		service.crawlPages(urls);
	}
	
	@Test
	public void testPagesThatAreNotMarfeelizable(){
		urls.add(new URLRepresentation("google.com",123));
		urls.add(new URLRepresentation("twitter.com",1234));
		
		service.crawlPages(urls);
	}
}
