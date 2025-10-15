package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	private WebDriver driver;
	private Actions action;
	private JavaScriptUtil js;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		js = new JavaScriptUtil(driver);
	}

	@Step("finding the element using {0}")
	public WebElement getElement(By locator) {
		ChainTestListener.log("The locator is: " + locator.toString());
		WebElement ele = driver.findElement(locator);
		highlightElement(ele);
		return ele;
	}

	private void highlightElement(WebElement element) {
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			js.flash(element);
		}
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public WebElement getElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toUpperCase()) {
		case "ID":
			locator = By.id(locatorValue);
			break;
		case "NAME":
			locator = By.name(locatorValue);
			break;
		case "CLASSNAME":
			locator = By.className(locatorValue);
			break;
		case "TAGNAME":
			locator = By.tagName(locatorValue);
			break;
		case "CSS":
			locator = By.cssSelector(locatorValue);
			break;
		case "XPATH":
			locator = By.xpath(locatorValue);
			break;
		case "LINKTEXT":
			locator = By.linkText(locatorValue);
			break;
		case "PARTIALLINKTEXT":
			locator = By.partialLinkText(locatorValue);
			break;
		default:
			System.out.println("Please pass the right" + locatorType);
			break;

		}
		return locator;
	}

	private void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new RuntimeException("=====VALUE CANNOT BE NULL=====");
		}
	}

	@Step("Entering the value: {1} into element: {0}")
	public void doSendkeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	@Step("Entering the value: {1} into element :{0}")
	public void doSendkeys(By locator, CharSequence... value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	@Step("Entering the value: {1} into element: {0}")
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		nullCheck(value);
		getElement(locatorType, locatorValue).clear();
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	@Step("Entering the value: {1} into element: {0}")
	public void doSendKeys(String locatorType, String locatorValue, CharSequence... value) {
		nullCheck(value);
		getElement(locatorType, locatorValue).clear();
		getElement(locatorType, locatorValue).sendKeys(value);
	}
@Step("clicking on element using: {0} ")
	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
	@Step("fetching the element text using: {0} ")
	public String doElementGetText(By locator) {
		String text = getElement(locator).getText();
		System.out.println(text);
		return text;
	}

	public String getElementDomAttributeValue(By locator, String attribute) {
		nullCheck(attribute);
		return getElement(locator).getDomAttribute(attribute);
	}

	public String getElementDomPropertyValue(By locator, String property) {
		nullCheck(property);
		return getElement(locator).getDomAttribute(property);
	}

	public boolean elementIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("Element not found on the page");
			return false;
		}

	}

	public boolean elementIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public boolean elementIsSelected(By locator) {
		return getElement(locator).isSelected();
	}

	/**
	 * 
	 * FindElementsUtils
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementTextList(By locator) {
		List<WebElement> list = getElements(locator);
		List<String> elementList = new ArrayList<String>();
		for (WebElement ele : list) {
			String val = ele.getText();
			if (val.length() != 0) {
				elementList.add(val);
			}
		}
		return elementList;
	}

	public boolean checkElementDispalyed(By locator) {
		if (getElements(locator).size() == 1) {
			System.out.println("Element :" + locator + "is dispalyed on the page one time");
			return true;
		}
		return false;
	}

	public boolean checkElementDispalyed(By locator, int expElementCount) {
		if (getElements(locator).size() == expElementCount) {
			System.out.println("Element :" + locator + "is dispalyed on the page" + expElementCount);
			return true;
		}
		return false;
	}

	public void clickElement(By locator, String lang) {
		List<WebElement> list = getElements(locator);
		System.out.println(list.size());

		for (WebElement e : list) {
			String text = e.getText();
			// instead of equals(), use contains() so that in future if new character or
			// special character is coming,
			// if using equals, it will not work.so better use Contains() instaed of
			// equals()
			if (text.contains(lang)) {
				e.click();
				break;
			}

		}
	}

	public void searchAndClickOnValue(By locator1, By locator2, String input, String searchVal)
			throws InterruptedException {
		doSendkeys(locator1, input);

		Thread.sleep(3000);
		clickElement(locator2, searchVal);
	}

	// **********************************DropDown utils************************//
	public boolean doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(index + " not found in the dropdown");
			return false;
		}
	}

	public boolean doSelectDropDownByvalue(By locator, String value) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByValue(value);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(value + " not found in the dropdown");
			return false;
		}
	}

	public boolean doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		try {
			select.selectByVisibleText(value);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(value + " not found in the dropdown");
			return false;
		}
	}

	public boolean selectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		System.out.println(options.size());
		boolean flag = false;
		for (WebElement list : options) {
			String text = list.getText();
			System.out.println(text);
			if (text.equals(value)) {
				System.out.println("country is " + text);
				list.click();
				flag = true;
				break;
			}

		}
		if (flag) {
			System.out.println(value + " is selected");
			return true;
		} else {
			System.out.println(value + "is not selected");
			return false;
		}
	}

	public List<String> getDropDownValueList(By locator) {
		Select select = new Select(driver.findElement(locator));
		List<WebElement> optionlist = select.getOptions();
		System.out.println(optionlist.size());

		List<String> optionValueList = new ArrayList<String>();
		for (WebElement list : optionlist) {
			String text = list.getText();
			optionValueList.add(text.trim());
		}
		return optionValueList;
	}

	public boolean getDropDownValueList(By locator, List<String> expList) {
		Select select = new Select(driver.findElement(locator));
		List<WebElement> optionlist = select.getOptions();
		System.out.println(optionlist.size());

		List<String> optionValueList = new ArrayList<String>();
		for (WebElement list : optionlist) {
			String text = list.getText();
			optionValueList.add(text.trim());
		}
		if (optionValueList.containsAll(expList)) {
			return true;
		} else {
			return false;
		}
	}

	// ***************Drop down to handle elements without select tag*************//

	public void selectChoice(By choice, By choiceList, String... choiceValue) throws InterruptedException {
		driver.findElement(choice).click();
		Thread.sleep(2000);

		List<WebElement> choices = driver.findElements(choiceList);
		System.out.println(choices.size());
		if (choiceValue[0].equalsIgnoreCase("All")) {
			for (WebElement el : choices) {
				el.click();
			}
		} else {
			for (WebElement ele : choices) {
				String text = ele.getText();
				System.out.println(text);
				for (String val : choiceValue) {
					if (text.equals(val)) {
						ele.click();
						break;
					}
				}

			}
		}
	}

	// ************************Actions methods***********//

	public void doMoveToElement(By locator) {
		action.moveToElement(getElement(locator)).build().perform();
	}

	public void handleParentSubMenu(By parentLocator, By childLocator) throws InterruptedException {
		doMoveToElement(parentLocator);
		Thread.sleep(2000);
		// getElement(childLocator).click(); OR
		doClick(childLocator);
	}

	public void handle4LevelMenuHandle(By level1Menu, By level2Menu, By level3Menu, By level4Menu)
			throws InterruptedException {

		// getElement(level1Menu).click(); OR
		doClick(level1Menu);
		Thread.sleep(2000);
		doMoveToElement(level2Menu);
		Thread.sleep(2000);
		doMoveToElement(level3Menu);
		Thread.sleep(2000);
		// getElement(level4Menu).click(); OR
		doClick(level4Menu);
	}

	public void doActionClick(By checkbox) {
		action.click(getElement(checkbox)).perform();
	}

	public void doActionSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).perform();
	}

	public void doSendkeysWithPause(By locator, String value, int pauseTime) {
		char[] charArray = value.toCharArray();
		for (char ch : charArray) {
			action.sendKeys(getElement(locator), String.valueOf(ch)).pause(pauseTime).perform();
		}
	}// *************WAIT UTILS*****************//

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForAllElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForAllElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		highlightElement(ele);
		return ele;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that isgreater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	@Step("Waiting for element using {0} and timeout : {1}")
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	@Step("Waiting for element and clicking on it using {0} and timeout : {1}")
	public void clickWhenready(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickWithWait(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public void sendKeysWithWait(By locator, int timeOut, CharSequence... value) {
		waitForElementVisible(locator, timeOut).sendKeys(value);
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String getTextAlert(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void sendKeysAlert(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);

	}

	// *********Wait for Title with titleConains---fraction of title
	public String waitForTitleContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	//
	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	// *********Wait for Url with URLContains---fraction of URL
	public String waitForURLContains(int timeOut, String fractionURL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlContains(fractionURL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

	// ******wait for Url with URLIs---exact URL
	public String waitForExactURL(int timeOut, String URL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlToBe(URL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

//*****WAit for Frame
	public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitchToIt(String frameIdOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
	}

	public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToIt(WebElement FrameEle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameEle));
	}

	// ***********Wait for window***//
	public boolean waitForWindow(int expectedNoOfWindows, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindows));
		} catch (Exception e) {
			System.out.println("Expected number of windows are not correct");
			return false;
		}
	}

	// *********FluentWiat methods*******
//	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
//		Wait<WebDriver> wait = new FluentWait<>(driver)
//		        .withTimeout(Duration.ofSeconds(20))
//		        .pollingEvery(Duration.ofSeconds(2))
//		        .ignoring(NoSuchElementException.class)
//				.ignoring(StaleElementReferenceException.class)
//				.withMessage("===Element is not found====");
//
//		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//	}
//	
//	
//	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
//		Wait<WebDriver> wait =  new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(timeOut))
//				.pollingEvery(Duration.ofSeconds(pollingTime))
//				.ignoring(NoSuchElementException.class)
//				.ignoring(StaleElementReferenceException.class)
//				.withMessage("===Element is not found====");
//
//		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
				.toString();
		return Boolean.parseBoolean(flag);// true
	}
}
