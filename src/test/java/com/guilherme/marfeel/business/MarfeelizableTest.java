package com.guilherme.marfeel.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.guilherme.marfeel.model.WebPage;

public class MarfeelizableTest {
	
	private WebPage pageMock;
	private TitleMarfeelizable titleRule;
	
	@Before
	public void setUp() {
		 pageMock = Mockito.mock(WebPage.class);
		 titleRule = new TitleMarfeelizable(new BasicMarfeelizable());
	}

	@Test
	public void testIsMarfeelizableEnglish() {
		Mockito.when(pageMock.getTitle()).thenReturn("BBC news");
		
		boolean result = titleRule.isMarfeelizable(pageMock);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsMarfeelizableSpanish() {
		Mockito.when(pageMock.getTitle()).thenReturn("Espana Noticias");
		
		boolean result = titleRule.isMarfeelizable(pageMock);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsMarfeelizableLowerCase() {
		Mockito.when(pageMock.getTitle()).thenReturn("espana noticias");
		
		boolean result = titleRule.isMarfeelizable(pageMock);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsNotMarfeelizable() {
		Mockito.when(pageMock.getTitle()).thenReturn("Google");
		
		boolean result = titleRule.isMarfeelizable(pageMock);
		
		Assert.assertFalse(result);
	}
	
	@Test
	public void testEmptyTitle() {
		Mockito.when(pageMock.getTitle()).thenReturn("");
		
		boolean result = titleRule.isMarfeelizable(pageMock);
		
		Assert.assertFalse(result);
	}
	

}
