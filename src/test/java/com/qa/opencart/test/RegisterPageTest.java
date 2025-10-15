package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void RegisterpageSetup() {
		registerpage = loginpage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] registerPageTestData() {
		return new Object[][] { { "VIshal", "RAM",  "2765478887", "Test@123", "Yes" },
				{ "Archana", "Rom", "2765478765", "archtest@123", "no" },
				{ "Aditya", "babu",  "2765488887", "adityaTest@123", "Yes" } };
	}

	@Test(dataProvider = "getUserRegData")
	public void registerUserTest(String firstname, String lastname,  String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registerpage.userRegistration(firstname, lastname,  telephone, password, subscribe));
	}
//	@Test
//	public void registerUserTest()
//	{
//		Assert.assertTrue(registerpage.userRegistration("VIshal", "RAM", "Vishal123@gmail.com", "2765478887", "Test@123", "Yes"));
//	}
	@DataProvider
	public Object[][] getUserRegData()
	{
		Object[][] testData = ExcelUtil.getTestData(REGISTER_TEST_DATA);
		return testData;
	}
}
