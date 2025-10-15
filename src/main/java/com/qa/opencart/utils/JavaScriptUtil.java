package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void getTitleByJS() {
		String title = js.executeScript("return document.title;").toString();
		System.out.println(title);
	}

	public void getUrlByJS() {
		String url = js.executeScript("return document.URL").toString();
		System.out.println(url);
	}

	public void refreshBrowserByRefresh() {
		js.executeScript("history.go(0)");
	}

	public void navigateToBackPage() {
		js.executeScript("history.go(-1)");
	}

	public void navigateToForwardPage() {
		js.executeScript("history.go(1)");
	}
	
	public void generateJsAlert(String alert) {
		js.executeScript("alert('"+alert+"')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert().dismiss();
	}
	public void getPageInnerText()
	{
		String text=js.executeScript("return document.documentElement.innerText").toString();
		System.out.println(text);
	}
	public void scrollPageDown()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight");
		
	}
	public void scrollPageDown(String height)
	{
		js.executeScript("window.scrollTo(0,'"+height+"')");
		
	}
	public void scrollPageUp()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight,0");
		
	}
	public void scrollIntoView(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	public void drawBorder(WebElement element)
	{
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//blue
		for (int i = 0; i < 7; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// blue
		}
	}

	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	public void zoomChromeEdgeFirefox(String zoomPercentage) {
		String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'";
		js.executeScript(zoom);
	}
	
	/**
	 * example: "document.body.style.MozTransform = 'scale(0.5)'; ";
	 * @param zoomPercentage
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		js.executeScript(zoom);

	}
	public void clickElementByJs(WebElement element)
	{
		js.executeScript("arguments[0].click()", element);
	}
	public void sendKeysByJSID(String id,String mailID)
	{
		js.executeScript("document.getElementById('"+id+"').value='"+mailID+"'");
	}
	public void sendKeysCSSByJS(String value,String mailID)
	{
		js.executeScript("document.querySelector('"+value+"').value='"+mailID+"'");
	}
}
