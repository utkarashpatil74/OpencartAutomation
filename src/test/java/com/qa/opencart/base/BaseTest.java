package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactoy;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

import io.qameta.allure.Step;

public class BaseTest {
	DriverFactoy df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage productPage;
	protected SoftAssert softassert;
	protected ShoppingCartPage shoppingCartPage;
	protected RegisterPage registerPage;
	
	@Step("Launching the {0} browser snd initializing the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactoy();
		prop = df.initProp();
		
		if(prop!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softassert = new SoftAssert();
	}
	
	@Step("Closing the browser")
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
