package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private final By headers = By.cssSelector("div#content>h2");
	private final By search=By.xpath("//input[@name='search']");
	private final By searchIcon= By.cssSelector("div#search button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		String accountpageTitle = eleutil.waitForTitleIs(DEFAULT_TIMEOUT, ACCOUNT_PAGE_TITLE);
		System.out.println("Account page title :" + accountpageTitle);
		return accountpageTitle;
	}

	public String getAccountPageURL() {
		String accountpageURL = eleutil.waitForURLContains(DEFAULT_TIMEOUT, ACCOUNT_PAGE_FRACTION_URL);
		System.out.println("Account page URL :" + accountpageURL);
		return accountpageURL;
	}

	public List<String> getAccountPageHeaders() {
		List<WebElement> headersList = eleutil.getElements(headers);
		List<String> headersListValue = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersListValue.add(text);
		}
		System.out.println("Header values are: " + headersListValue);
		return headersListValue;
	}
	public SearchPage doSearch(String searchKey)
	{
		System.out.println("Search Result key is: "+searchKey);
		eleutil.doSendkeys(search, searchKey);
		eleutil.doClick(searchIcon);
		return new SearchPage(driver);
	}
}
