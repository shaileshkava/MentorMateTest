package com.mentormanate.MentorMateTest.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mentormanate.MentorMateTest.Manager.DriverServices;

public class ReusableMethods {
	
	private WebDriver driver;
	
	public ReusableMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean bVisibleElelemnt(WebElement we) {
		boolean bVisibility = true;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(we));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			bVisibility = false;
		}

		return bVisibility;
	}

	public boolean bClickableElelemnt(WebElement we) {
		boolean bClickable = true;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(we));

		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
			bClickable = false;
		}

		return bClickable;
	}
	
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("Current Window State : "
				+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
		// wait.until(new Predicate<WebDriver>() {
		wait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				System.out.println("Current Window State : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
	
	public String getCurrentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	}

}
