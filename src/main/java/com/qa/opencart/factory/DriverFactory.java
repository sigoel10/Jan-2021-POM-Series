package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Indra Goel
 *
 */
public class DriverFactory {
	WebDriver driver;
	Properties prop;
	private OptionsManager optionsManager;

	public static String highlight = null;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browserName
	 * @return this method will return webdriver
	 */
	public WebDriver init_driver(Properties prop) {

		highlight = prop.getProperty("highlight"); //highlighting the element
		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser").trim(); //browser value - trimming space if its in config

		System.out.println("Browser name is: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions()); //this will initialize the driver
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); //initializing driver with thread local
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser: " + browserName);
		}
//		driver().manage().window().maximize();
//		driver().manage().deleteAllCookies();
//		driver().get(prop.getProperty("url").trim());
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	/**
	 * synchronized means when multiple threads are attacking, whenever we fetch the
	 * value it should be in synchronized way.
	 * 
	 * @return webdriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * 
	 * @return this method will return Properties Object
	 */
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();

		String env = System.getProperty("env");//this is used to pass different environments
		System.out.println("Running on Environment : ---> On PROD");

		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Running on Environment : ---> " + env);
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * This method take screenshot
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
