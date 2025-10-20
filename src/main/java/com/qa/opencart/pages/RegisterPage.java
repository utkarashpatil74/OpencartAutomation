package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegisterPage {
	
	private WebDriver driver;
    private ElementUtil eleUtil;
    
    private By firstNameInput=By.name("firstname");
    private By lastNameInput=By.id("input-lastname");
    private By emailInput=By.name("email");
    private By telephoneInput=By.id("input-telephone");
    private By passwordInput=By.name("password");
    private By confirmPassInput=By.id("input-confirm");
    private By privacyPolicyCheckBox=By.name("agree");
    private By continueButton=By.cssSelector("input.btn.btn-primary");
    private By successMessage=By.tagName("h1");
    private By logoutLink=By.linkText("Logout");
    private By registerLink=By.linkText("Register");
	

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	@Step("User reg with: {0}, {1}, {2), {3}, {4}, {5}")
	public boolean fillupRegistrationForm(String firstName, String lastName, String email, String telephone, String password) {
		eleUtil.doSendKeys(firstNameInput,firstName, 5);
		eleUtil.doSendKeys(lastNameInput,lastName);
		eleUtil.doSendKeys(emailInput, email);
		eleUtil.doSendKeys(telephoneInput, telephone);
		eleUtil.doSendKeys(passwordInput, password);
		eleUtil.doSendKeys(confirmPassInput, password);
		eleUtil.doClick(privacyPolicyCheckBox, 2);
		eleUtil.doClick(continueButton, 2);
		
		String actMessage=eleUtil.waitForElementVisible(successMessage, 3).getText();
		
		if(actMessage.contains(AppConstants.USER_REG_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	

}
