package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailId =By.id("input-email");
	private By password=By.id("input-password");
	private By forgotpassword=By.linkText("Forgotten Password");
	private By loginButton=By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	@Step("Getting login page title....")
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,5);
		System.out.println("login page title : "+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
		System.out.println("Page URL is "+url);
		return url;
		
	}
	@Step("Getting the state of forgot password link....")
	public Boolean isForgotPwdLinkExist() {
		 return eleUtil.isElementDisplayed(forgotpassword);
		 
		
	}
	
	@Step("Login with username : {0} and password : {1}")
	public AccountsPage doLogin(String email,String passwordInput) {
		System.out.println("Entering the login details and performing login ");
		eleUtil.waitForElementPresence(emailId,5).sendKeys(email);
		eleUtil.doSendKeys(password, passwordInput);
		eleUtil.doClick(loginButton);
		return new AccountsPage(this.driver);
	}
	
	@Step("Navigating to the register page....")
	public RegisterPage selectRegister() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
