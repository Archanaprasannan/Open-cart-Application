package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.MEDIUM_TIMEOUT;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// public constructors
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil=new ElementUtil(driver);
	}

	// private final locators
	private final By productHeader = By.xpath("//div[@id='content']//h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productmetadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productpricedata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	Map<String,String>productmap;
	// public methods/actions
	public String getProductInfoHeader()
	{
		String productHeaderval=eleutil.waitForElementVisible(productHeader, MEDIUM_TIMEOUT).getText();
		System.out.println("Product header: "+productHeaderval);
		return productHeaderval;
	}
	public int getProductImageCount()
	{
		int imageCount=eleutil.waitForAllElementsVisible(productImages, DEFAULT_TIMEOUT).size();
		System.out.println("Total image count :"+imageCount);
		return imageCount;
		
	}
	public Map<String,String> getProductDetails()
	{
		//Hashmap will not maintain the order
		//productmap=new HashMap<String,String>();
		//LinkedHashMap will maintain the order
		//productmap=new LinkedHashMap<String,String>();
		//Treemap will give sorted value of keys
		productmap=new TreeMap<String,String>();
		productmap=new LinkedHashMap<String,String>();
		productmap.put("productHeader", getProductInfoHeader());
		productmap.put("productimagecount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getPriceData();
		System.out.println("Full product details: "+productmap);
		return productmap;
	}
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: Out Of Stock
	private void getProductMetaData()
	{
		List<WebElement> metadata = eleutil.waitForAllElementsVisible(productmetadata, DEFAULT_TIMEOUT);
		for(WebElement e: metadata)
		{
			String text=e.getText();
			String[] split = text.split(":");
			String metakey=split[0].trim();
			String metaval=split[1].trim();
			productmap.put(metakey, metaval);
		}
	}
	//$2,000.00
	//Ex Tax: $2,000.00
	private void getPriceData()
	{
		List<WebElement> pricedata = eleutil.waitForAllElementsVisible(productpricedata, DEFAULT_TIMEOUT);
		String productPrice=pricedata.get(0).getText();
		String exTaxPrice=pricedata.get(1).getText().split(":")[1].trim();
		productmap.put("productPrice", productPrice);
		productmap.put("exTaxPrice", exTaxPrice);
	}

}
