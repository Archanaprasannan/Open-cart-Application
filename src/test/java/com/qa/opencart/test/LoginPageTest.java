package com.qa.opencart.test;

import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
//priority given by testng
//severity given by allure
@Feature("New login page for Open cart application")
@Epic("Epic No: 2345 Login page with new UI")
@Story("Story No:2656 User can login using email and password")
public class LoginPageTest extends BaseTest {
	@Test
	@Description("Checking Open cart application Title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Archana")
	@Issue("DefectId:2657")
	@TmsLink("Tms ID 1")
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		ChainTestListener.log("The title is: "+actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	@Test
	@Description("Checking Open cart application URL")
	@Severity(SeverityLevel.MINOR)
	@Owner("Archana")
	@Issue("DefectId:2656")
	@TmsLink("Tms ID 2")
	public void loginPageURLTest() {
		String actTitle = loginpage.getLoginPageUrl();
		Assert.assertTrue(actTitle.contains(LOGIN_PAGE_FRACTION_URL));
	}
	@Test
	@Description("Checking Open cart application has forgot password link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Archana")
	@Issue("DefectId:2696")
	@TmsLink("Tms ID 3")
	public void loginPageForgetPwdExistTest() {
		Assert.assertTrue(loginpage.isForgetPwdLinkExist());
	}
	@Test(priority=Short.MAX_VALUE)
	@Description("Checking Open cart application login using valid credentials")
	@Severity(SeverityLevel.MINOR)
	@Owner("Archana")
	@Issue("DefectId:2616")
	@TmsLink("Tms ID 4")
	public void doLoginTest() {
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actTitle=accpage.getAccountPageTitle();
		Assert.assertEquals(actTitle, ACCOUNT_PAGE_TITLE);
	}
}
