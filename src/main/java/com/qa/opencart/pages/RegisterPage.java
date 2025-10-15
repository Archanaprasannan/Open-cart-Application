package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtil;

import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private final By firstname = By.id("input-firstname");
	private final By lastname = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmpassword = By.id("input-confirm");

	private final By subscribeYes = By.xpath("//div[@class='form-group']//input[@value='1']");
	private final By subscribeNo = By.xpath("//div[@class='form-group']//input[@value='0']");
	private final By agreeCheckbox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit']");

	private final By successMsg = By.xpath("//div[@id='content']/h1");

	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	public boolean userRegistration(String firstname, String lastname, String telephone, String password,
			String subscribe) {
		eleutil.waitForElementVisible(this.firstname, DEFAULT_TIMEOUT).sendKeys(firstname);
		eleutil.doSendkeys(this.lastname, lastname);
		eleutil.doSendkeys(this.email, StringUtil.getRandomEmailID());
		eleutil.doSendkeys(this.telephone, telephone);
		eleutil.doSendkeys(this.password, password);
		eleutil.doSendkeys(this.confirmpassword, password);
		if(subscribe.equals("Yes"))
		{
			eleutil.doClick(subscribeYes);
		}
		else
		{
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckbox);
		eleutil.doClick(continueButton);
		
		if(eleutil.waitForElementVisible(successMsg, MEDIUM_TIMEOUT).getText().contains(REGISTER_SUCCESS_MESSAGE))
		{
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		return false;
		
	}
}
