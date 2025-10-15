package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.MEDIUM_TIMEOUT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// public constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// private final locators
	private final By email = By.id("input-email");
	private final By pwd = By.id("input-password");
	private final By loginButton = By.xpath("//input[@type='submit']");
	private final By forgotPassword = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");

	// public methods/actions
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(DEFAULT_TIMEOUT, LOGIN_PAGE_TITLE);
		System.out.println("Login page title::: " + title);
		return title;
	}

	@Step("getting login page url")
	public String getLoginPageUrl() {
		String url = eleutil.waitForURLContains(DEFAULT_TIMEOUT, LOGIN_PAGE_FRACTION_URL);
		System.out.println("Login page URL: " + url);
		return url;
	}

	@Step("checking forgot password exist")
	public Boolean isForgetPwdLinkExist() {
		return eleutil.elementIsDisplayed(forgotPassword);
	}

	@Step("login using valid username: {0} and password:{1}")
	public AccountPage doLogin(String username, String pswd) {
		System.out.println("User credentials:" + username + ":" + pswd);
		eleutil.waitForElementVisible(email, MEDIUM_TIMEOUT).sendKeys(username);
		eleutil.doSendkeys(pwd, pswd);
		eleutil.doClick(loginButton);
		return new AccountPage(driver);
//		String title=eleutil.waitForTitleIs(DEFAULT_TIMEOUT, HOME_PAGE_TITLE);
//		System.out.println("Title of account page:"+title);
//		return title;
	}

	@Step("navigating to registartion page")
	public RegisterPage navigateToRegisterPage() {
		eleutil.clickWhenready(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
}
