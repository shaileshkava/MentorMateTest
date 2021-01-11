package com.mentormanate.MentorMateTest.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.mentormanate.MentorMateTest.Manager.DriverServices;

public class CaptureScreenshot {
	
	private DriverServices driverService;
	private ReusableMethods rm;
	public static WebDriver driver;
	
	
	public CaptureScreenshot(DriverServices driverService) {
		this.driverService = driverService;
		this.driver = driverService.getDriver();
		this.rm = new ReusableMethods(driver);
	}
	
	public void captureScreenshot(String sDir, String bFileName) {
		bFileName = bFileName+"_"+rm.getCurrentDateTime()+".png";
		File directory = new File(sDir);
		
		if(!directory.exists())
			directory.mkdirs();
		
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			System.out.println("Screenshot Directory = "+directory.getAbsolutePath() + File.separator + bFileName);
			FileUtils.copyFile(screenshot, new File(directory.getAbsolutePath() + File.separator + bFileName));
		} catch (IOException e) {
			System.out.println("in catch");
			e.printStackTrace();
		}
	}
	
	public String captureScreenshot(String aPath) {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(aPath));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return aPath;
	}
	
	public byte[] captureScreenshot() {
		byte[] screenshot;
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}
	
}
