package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;	
	private By shoppingCartHeader =By.tagName("h1");


	public ShoppingCartPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getShoppingCartTitle() {
		String actTitle=driver.getTitle();
		return actTitle;
	}
	
	public String getShoppingCartHeader() {
		return eleUtil.waitForElementVisible(shoppingCartHeader,5).getText();
	}
	
	public String getShoppingCartURL() {
		return driver.getCurrentUrl();
	}

}
