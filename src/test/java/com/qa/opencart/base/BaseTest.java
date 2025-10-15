package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

import io.qameta.allure.Description;

@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;
	protected AccountPage accpage;
	protected SearchPage searchpage;
	protected ProductInfoPage productinfopage;
	protected RegisterPage registerpage;

	@Description("init the driver and properties")
	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initBrowser(prop);
		loginpage = new LoginPage(driver);
		// we will not write accountapge init here bcz login page also using the same
		// setUp() method, in that time, there is no point
		// in adding accountpage init here.
		// we are doing accountpage init in accountpage test.
		// this setUp() method is only for starting the test execution from the login.
		// **********accpage=new AccountPage(driver);
	}

	@AfterMethod // will be running after each @Test method
	public void getScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		// if you want to take screenshot for both pass and fail,use only the below line
		// instaed of if condition...remove the if condition
		// ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
	}

	@Description("closing the browser-")
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
