package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ShoppingCartPageTest extends BaseTest {

	@BeforeClass
	public void searchResultSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void getShoppingCartPageTitleTest() {
		searchPage = accPage.dosearch("macbook");
		productPage = searchPage.selectProduct("MacBook Pro");
		shoppingCartPage = productPage.selectShoppingCartLink();
		Assert.assertTrue(shoppingCartPage.getShoppingCartTitle().contains("Shopping Cart"));
	}
	
	@Test
	public void getShoppingCartPageURLTest() {
		searchPage = accPage.dosearch("macbook");
		productPage = searchPage.selectProduct("MacBook Pro");
		shoppingCartPage = productPage.selectShoppingCartLink();
		Assert.assertTrue(shoppingCartPage.getShoppingCartURL().contains("checkout/cart"));
	}
	
	@Test
	public void getShoppingCartPageHeaderTest() {
		searchPage = accPage.dosearch("macbook");
		productPage = searchPage.selectProduct("MacBook Pro");
		shoppingCartPage = productPage.selectShoppingCartLink();
		Assert.assertTrue(shoppingCartPage.getShoppingCartHeader().contains("Shopping Cart"));
	}

}
