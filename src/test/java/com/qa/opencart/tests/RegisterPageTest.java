package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Step;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.selectRegister();
	}
	
	@DataProvider
	public Object[][] getUserRegistrationData() {
		return new Object[][] {
			{"Utkarsh","Patil","74532324","utkarsh@123"},
			{"Vaibhav","Patil","12464738","vaibhavp@336"},
			{"Bhagyashree","Patil","23465782","dfrg@1345"},
			{"Nalini","Patil","2353909","n@847584"},
		};
	}
	
	@DataProvider
	public Object[][] getUserResitrationDataFromExcel(){
		return ExcelUtil.getTestData("register");
	} 
	@Step("Checking user registration")
	@Test(dataProvider = "getUserRegistrationData")
	public void userRegistrationTest(String firstName,String lastName,String phoneNumber,String password) {
		
	Assert.assertTrue(registerPage.fillupRegistrationForm(firstName,lastName,StringUtils.getRandomMailId(),phoneNumber,password));
	}
}
