package com.guilherme.marfeel.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.marfeel.service.WebCrawlerService;

@RestController
public class WebCrawlerController {
	@Autowired
	private WebCrawlerService service;

	@RequestMapping(value="/read", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Void> readURLs(@RequestBody List<URLRepresentation> urls) {
		try {
			service.crawlPages(urls);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}

}
