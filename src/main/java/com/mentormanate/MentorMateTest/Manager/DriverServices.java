package com.mentormanate.MentorMateTest.Manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.mentormanate.MentorMateTest.Utils.ReadProperties;
import com.mentormanate.MentorMateTest.Utils.SetBrowserName;

public class DriverServices {
	
	private WebDriver driver;
	
	ReadProperties iReadProperties = new ReadProperties();
	SetBrowserName sName = new SetBrowserName();
	private String browserName = sName.getBrowsername();
	
	public DriverServices() {
		launchBrowser();
	}
	
	public String getURL() {
		return iReadProperties.getURL();
	}
	
	private void launchBrowser() {
		if(driver == null)
			driver = getBrowser();
		
		
	}
	
	public WebDriver getDriver() {
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver getBrowser() {
		System.out.println("BrowserName is "+browserName);
		switch (browserName) {

		case BrowserType.CHROME:
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
				
		case BrowserType.FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();

		default:
			throw new RuntimeException("Invalid browser type: " + browserName);
		}
	}
}