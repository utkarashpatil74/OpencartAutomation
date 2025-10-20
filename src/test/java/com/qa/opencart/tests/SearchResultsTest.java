package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTest extends BaseTest {

	@BeforeClass
	public void searchResultSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void searchResultsTest() {
		searchPage = accPage.dosearch("macbook");
		int actualProductCount=searchPage.getProductCount();
		Assert.assertEquals(actualProductCount,3);
	}

}
