package com.qa.opencart.test;


import static com.qa.opencart.constants.AppConstants.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {

	
	@BeforeClass
	public void productInfoSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// We are not adding below 2 lines here because in those steps, product code
		// will chnge each time
		// searchpage=accpage.doSearch(prop.getProperty("searchkey"));
		// productinfopage=searchpage.selectProduct("MacBook Pro");
	}
	//direct data
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "macbook", "MacBook Air" },
				{ "macbook", "MacBook" }, { "samsung", "Samsung SyncMaster 941BW" },
				{ "samsung", "Samsung Galaxy Tab 10.1" } };
	}


	@Test(dataProvider="getProductTestData")
	public void productInfoHeaderTest(String searchkey,String header ) {
		searchpage = accpage.doSearch(searchkey);
		productinfopage = searchpage.selectProduct(header);
		String actHeader = productinfopage.getProductInfoHeader();
		Assert.assertEquals(actHeader, header);
	}
	//this is the expected data
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "macbook", "MacBook Pro" ,4}, { "macbook", "MacBook Air" ,4},
				{ "macbook", "MacBook" ,5}, { "samsung", "Samsung SyncMaster 941BW",1 },
				{ "samsung", "Samsung Galaxy Tab 10.1",7 } };
	}

	@Test(dataProvider="getProductCSVData")
	public void productInfoImageCountTest(String searchKey,String productname,String imagecount) {
		searchpage = accpage.doSearch(searchKey);
		productinfopage = searchpage.selectProduct(productname);
		int actCount = productinfopage.getProductImageCount();
		Assert.assertEquals(String.valueOf(actCount), imagecount);
	}

	@Test
	public void getProductDataTest() {
		searchpage = accpage.doSearch(prop.getProperty("searchkey"));
		productinfopage = searchpage.selectProduct("MacBook Pro");
		Map<String, String> actproductDetails = productinfopage.getProductDetails();
//		Assert.assertEquals(actproductDetails.get("Brand"), "Apple");
//		Assert.assertEquals(actproductDetails.get("Product Code"), "Product 18");
//		Assert.assertEquals(actproductDetails.get("Reward Points"), "800");
//		Assert.assertEquals(actproductDetails.get("Availability"), "Out Of Stock");
//		Assert.assertEquals(actproductDetails.get("productPrice"), "$2,000.00");
//		Assert.assertEquals(actproductDetails.get("exTaxPrice"), "$2,000.00");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(actproductDetails.get("Brand"), "Apple");
		softassert.assertEquals(actproductDetails.get("Product Code"), "Product 18");
		softassert.assertEquals(actproductDetails.get("Reward Points"), "800");
		softassert.assertEquals(actproductDetails.get("Availability"), "Out Of Stock");
		softassert.assertEquals(actproductDetails.get("productPrice"), "$2,000.00");
		softassert.assertEquals(actproductDetails.get("exTaxPrice"), "$2,000.00");
		softassert.assertAll();

	}
	//data from excel
	@DataProvider
	public Object[][] getProductSheetData()
	{
		Object[][] testData = ExcelUtil.getTestData(PRODUCTINFO_TEST_DATA);
		return testData;
	}
	@DataProvider
	public Object[][] getProductCSVData()
	{
		Object[][] testData = CSVUtil.getCSVData("product");
		return testData;
	}
}
