package com.mentormanate.MentorMateTest.Hooks;

import org.openqa.selenium.WebDriver;

import com.mentormanate.MentorMateTest.Manager.DriverServices;
import com.mentormanate.MentorMateTest.Utils.CaptureScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class GeneralHooks {
	
	private DriverServices driverService;
	private CaptureScreenshot captureScreenshot;
	private WebDriver driver;
	
	public GeneralHooks(DriverServices driverService, CaptureScreenshot captureSCreenshot) {
		this.driverService = driverService;
		this.driver = driverService.getDriver();
		this.captureScreenshot = captureSCreenshot;
	}
	
	@After (order=0)
	public void closeBrowser() {
		  if(driver != null) 
			  driver.quit();
	}
	
	@After
	public void onError(Scenario scenario) {
		if(scenario.isFailed()) {
			System.out.println("failed scenario");
			captureScreenshot.captureScreenshot("screenshot", "FailedScenario");
			scenario.attach(captureScreenshot.captureScreenshot(), "image/png", "FailedScenario");
		}
	}
	
}
