package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> metaProductList=new LinkedHashMap<>();
	
	private By productHeader=By.tagName("h1");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productQuantityInput=By.id("input-quantity");
	private By productAddToCartButton=By.id("button-cart");
	private By productAddSuccessMessage=By.xpath("//div[contains(@class,'alert-success')]");
	private By shoppingCartLink=By.linkText("shopping cart");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		return eleUtil.waitForElementVisible(productHeader,5).getText();
	}
//	
	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImages, 10).size();
	}
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
	private void getProductMetaData() {
		List<WebElement> productMeta=eleUtil.getElements(productMetaData);
		for(WebElement e:productMeta) {
			String s=e.getText();
			String metaKey= s.split(":")[0].trim();
			String metaValue=s.split(":")[1].trim();
			metaProductList.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
    private void getProductPriceData() {
		List<WebElement> productPriceMeta=eleUtil.getElements(productPriceData);
		String metaPriceKey="ProductPrice";
		String metaPriceValue=productPriceMeta.get(0).getText();
		metaProductList.put(metaPriceKey, metaPriceValue);
		String metaPriceKey2="ExTax";
		String metaPriceValue2=productPriceMeta.get(1).getText().split(":")[1].trim();
		metaProductList.put(metaPriceKey2, metaPriceValue2);
		
	}
    
    public Map<String, String> getProductInfoData() {
    	getProductMetaData();
    	getProductPriceData();
    	metaProductList.put("header",getProductHeader());
    	metaProductList.put("productImages",String.valueOf(getProductImageCount()));
    	System.out.println(metaProductList);
    	return metaProductList;
    }
    
    private String productAddToCart(int quantity) {
    	eleUtil.doSendKeys(productQuantityInput, String.valueOf(quantity));
    	eleUtil.doClick(productAddToCartButton);
    	System.out.println(eleUtil.waitForElementVisible(productAddSuccessMessage,10).getText());
    	return eleUtil.waitForElementVisible(productAddSuccessMessage,10).getText();
    }
    
    public ShoppingCartPage selectShoppingCartLink() {
    	productAddToCart(2);
    	eleUtil.doClick(shoppingCartLink,5);
    	return new ShoppingCartPage(driver);
    	
    }
}
