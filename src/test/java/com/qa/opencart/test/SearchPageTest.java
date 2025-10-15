package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchPageTest extends BaseTest{

	@BeforeClass
	public void SearchSetup()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void searchProductCountTest()
	{
		searchpage=accpage.doSearch(prop.getProperty("searchkey"));
		int actCount=searchpage.searchResultProductCount();
		Assert.assertEquals(actCount, 3);
	}
}
