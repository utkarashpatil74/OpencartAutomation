package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink =By.linkText("Logout");
	private By myAccountLink =By.linkText("My Account");
	private By headers=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	
	
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title=eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE,5);
		System.out.println("Account page title : "+title);
		return title;
	}
	
	public String getAccPageURL() {
		String url=eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
		System.out.println("acc Page URL is "+url);
		return url;
		
	}
	
	public Boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, 5).isDisplayed();
	}
	
	public Boolean myAccountinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, 5).isDisplayed();
	}
	
	public SearchResultsPage dosearch(String searchKey) {
		System.out.println("Searching for "+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	
	public List<String> getAccPageHeadersList() {
		List<WebElement> accPageHeaders=eleUtil.getElements(headers);
		List<String> accPageHeaderList=new ArrayList<>();
		for(WebElement e:accPageHeaders) {
			String text=e.getText();
			accPageHeaderList.add(text);
		}
		return accPageHeaderList;
	}

}
