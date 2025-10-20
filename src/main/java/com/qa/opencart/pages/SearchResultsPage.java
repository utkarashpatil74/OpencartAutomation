package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProducts=By.cssSelector("div.product-thumb");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public int getProductCount() {
		return eleUtil.waitForElementsVisible(searchProducts,5).size();
		
	}
	
	public ProductInfoPage selectProduct(String productname) {
		System.out.println("Searching for "+productname);
		eleUtil.waitForElementVisible(By.linkText(productname),10).click();
		return new ProductInfoPage(driver);
	}

}
