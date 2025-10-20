package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactoy {

	WebDriver driver;
	protected Properties prop;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");
		Log.info("Browser name is: " + browserName);
		highlight = prop.getProperty("highlight");

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		default:
			Log.error("Enter a correct browser name " + browserName);
			throw new BrowserException("NO BROWSER FOUND... " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * take screenshot
	 */

	

	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is given.... hence running it on qa env....");
				ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/config.dev.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
					break;
				case "preprod":
					ip = new FileInputStream("./src/test/resource/config/config.preprod.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/config.properties");
					break;
				default:
					System.out.println("Please pass the correct environment name: " + envName);
					throw new FrameworkException(AppError.WRONG_ENV_NAME + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}