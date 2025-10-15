package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

import io.qameta.allure.Step;

public class DriverFactory {
	// if you amke driver as static, it will prevent parallel execution.
	WebDriver driver;
	Properties prop;
	OptionsManager optionsmanager;
	public static String highlight;
	// intitialize the ThreadLocal and add the generics as webdriver
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to intialize the driver based upon the browser name
	 * 
	 * @param browserName
	 */
	@Step("Init teh driver using prop: {0}")
	public WebDriver initBrowser(Properties prop) {
		// not get the details about prop in report..it only give deatils about the
		// things that directly connected with test class.
		// ChainTestListener.log("Properties used: "+prop.toString());
		String browserName = prop.getProperty("browser");
		System.out.println("BrowserName : " + browserName);
		optionsmanager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// initialize the Thread local driver.
			tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			break;
		case "edge":
			tldriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
			break;
		case "firefox":
			tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			break;
		case "safari":
			tldriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass teh right browser name:" + browserName);
			throw new BrowserException("******INVALID BROWSER NAME");
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		// return the local copy of driver
		return getDriver();
	}

	// whenever using the driver, call this method
	// in all the methods which are returning the driver..it will automatically
	// chnges to thredalevel driver internally. in all page ca;ss and all.
	public static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This is used to initiazlise the config properties
	 * 
	 * @return
	 */
	// maven clean install -Denv="qa"
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fp = null;
		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("env is null....hence running the test on qa env by default.....");
				fp = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				System.out.println("running test on env :" + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fp = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					fp = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					fp = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					fp = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					fp = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					throw new FrameWorkException("=========INVALID ENVIRONMENT NAME=======" + envName);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			prop.load(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		// ************these lines added in single line
//		TakesScreenshot ts= (TakesScreenshot)getDriver();
//		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
//		return screenshotAs;

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		// want to take the screenshot in Byte format
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		// want to take the screenshot in encoded format
		// best way to take screenshot..smaller size
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
}
