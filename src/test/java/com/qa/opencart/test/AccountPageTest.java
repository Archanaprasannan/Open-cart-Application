package com.qa.opencart.test;


import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_HEADERS;
import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_TITLE;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchPage;
public class AccountPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetUp()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test
	public void accountPageTitleTest()
	{
		String acttitle=accpage.getAccountPageTitle();
		Assert.assertEquals(acttitle,ACCOUNT_PAGE_TITLE );
	}
	@Test
	public void accountPageURLTest()
	{
		String actUrl=accpage.getAccountPageURL();
		Assert.assertTrue(actUrl.contains(ACCOUNT_PAGE_FRACTION_URL) );
	}
	@Test
	public void accountPageHeadersList()
	{
		List<String> actHeaders= accpage.getAccountPageHeaders();
		Assert.assertEquals(actHeaders, ACCOUNT_PAGE_HEADERS);
	}
//	public void doSearchTest()
//	{
//		 searchpage=accpage.doSearch(prop.getProperty("searchkey"));
//		 int count=searchpage.searchResultProductCount();
//		 
//		 
//	}
}
