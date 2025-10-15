package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// public constructors
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// private final locators
	private final By searchResult = By.cssSelector("div.product-layout");

	// public methods/actions
	public int searchResultProductCount() {
		int searchCount= eleutil.waitForAllElementsVisible(searchResult,MEDIUM_TIMEOUT ).size();
		System.out.println("Total number of search product count: "+searchCount);
		return searchCount;
	}
	public ProductInfoPage selectProduct(String productName)
	{
		eleutil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
