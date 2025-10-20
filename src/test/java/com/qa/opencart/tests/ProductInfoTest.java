package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void Setup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}

	@Test(dataProvider="getProductData")
	public void productHeaderTest(String searchKey, String productHeader) {
		searchPage = accPage.dosearch(searchKey);
		productPage= searchPage.selectProduct(productHeader);
		Assert.assertEquals(productPage.getProductHeader(),productHeader);
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}
	
	@Test(dataProvider ="getProductImagesData" )
	public void productImagesTest(String searchKey, String productName, int imageCount) {
		searchPage = accPage.dosearch(searchKey);
		productPage= searchPage.selectProduct(productName);
		Assert.assertEquals(productPage.getProductImageCount(),imageCount);
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
//	$2,000.00
//	Ex Tax: $2,000.00
	@Test
	public void productInfoTest() {
		searchPage = accPage.dosearch("macbook");
		productPage= searchPage.selectProduct("MacBook Pro");
		Map<String,String> productDetailsActMap=productPage.getProductInfoData();
		softassert.assertEquals(productDetailsActMap.get("Brand"),"Apple");
		softassert.assertEquals(productDetailsActMap.get("Product Code"),"Product 18");
		softassert.assertEquals(productDetailsActMap.get("Reward Points"),"800");
		softassert.assertEquals(productDetailsActMap.get("ProductPrice"),"$2,000.00");
		softassert.assertEquals(productDetailsActMap.get("ExTax"),"$2,000.00");
		softassert.assertAll();
		
	}
}
