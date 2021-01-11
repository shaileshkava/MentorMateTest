package com.mentormanate.MentorMateTest.Pages;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.mentormanate.MentorMateTest.Utils.ReusableMethods;

public class PageClassProduct {

	private static WebDriver driver;
	private ReusableMethods rm; 
	public PageClassProduct(WebDriver driver) {
		PageClassProduct.driver = driver;
		rm = new ReusableMethods(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='nav-hamburger-menu']")
	public WebElement hanburger;

	@FindBy(xpath = "//input[@id='sp-cc-accept']")
	public WebElement acceptCoolie;

	@FindBy(xpath = "//div[@id='hmenu-customer-profile']")
	public WebElement profile;
	// Electronics & Comupters
	public String mainCategory = "//ul[@class=''hmenu hmenu-visible'']/li//div[text()=''{0}'']/parent::a";
	// Laptops
	public String subCategory = "//ul[@class=''hmenu hmenu-visible hmenu-translateX'']/li/a[text()=''{0}'']";
	// Display Size = 15 to 16 in, CPU Type = Intel Core i5, Storage Type = SSD
	public String productFilters = "//span[text()=''{0}'']/parent::div/following::ul/li//span[text()=''{1}'']/parent::a//label/i";
	// 4 Stars & Up
	public String filterByRating = "//div[@id=''reviewsRefinements'']/ul/li//span[text()=''{0}'']//ancestor::a";
	// Sort by = Price: Low to High
	public String sortBy_old = "//li[@class=''a-dropdown-item'']/a[text()=''{0}'']";
	public String sortBy = "//div[@aria-hidden=''false'']//li[@class=''a-dropdown-item'']/a[text()=''{0}'']";

	@FindBy(xpath = "//span[@class='a-dropdown-prompt']/parent::span")
	public WebElement drpDownSortBy;

	@FindBy(xpath = "//div[@id='reviewsRefinements']//span[text()='Clear']")
	public WebElement ratingClear;

	@FindBys(@FindBy(xpath = "//span[@class='a-price-whole']"))
	public List<WebElement> listProducts;

	@FindBys(@FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal'] | //span[@class='a-size-base-plus a-color-base a-text-normal']"))
	public List<WebElement> titleXpath;

	String strPriceXpath = "(//span[@class=''a-size-medium a-color-base a-text-normal''] | //span[@class=''a-size-base-plus a-color-base a-text-normal''])[{0}]";

		
	// Display Size = 15 to 16 in, CPU Type = Intel Core i5, Storage Type = SSD
	public void filterByProduct(String strProductCategory, String strProductName) {
		WebElement we = null;
		switch (strProductCategory) {
		case "display":
			we = driver.findElement(By.xpath(MessageFormat.format(productFilters, "Display Size", strProductName)));
			break;
		case "cpu":
			we = driver.findElement(By.xpath(MessageFormat.format(productFilters, "CPU Type", strProductName)));
			break;
		case "storage":
			we = driver.findElement(By.xpath(MessageFormat.format(productFilters, "Storage Type", strProductName)));
			break;
		default:
			break;
		}
		javaScriptScrollUptoElement(we);
		we.click();
	}

	public void clickElementByJavaScript(WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", we);
	}

	public void javaScriptScrollUptoElement(WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", we);
	}

	public void javaScriptClickElement(WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", we);
	}

	public void clickElementByName(String strElement, String args) {

		WebElement webElement = driver.findElement(By.xpath(MessageFormat.format(strElement, args)));

		rm.bVisibleElelemnt(webElement);
		rm.bClickableElelemnt(webElement);
		javaScriptScrollUptoElement(webElement);
		webElement.click();
	}

	public void filterByRatings(String filters) {
		System.out.println("Starred Element = " + By.xpath(MessageFormat.format(filterByRating, filters)));
		WebElement webElement = driver.findElement(By.xpath(MessageFormat.format(filterByRating, filters)));
		rm.bClickableElelemnt(webElement);
		javaScriptClickElement(webElement);
		rm.bVisibleElelemnt(ratingClear);
	}

	public void sortByProducts(String filters) {
		rm.bVisibleElelemnt(drpDownSortBy);
		javaScriptScrollUptoElement(drpDownSortBy);
		javaScriptClickElement(drpDownSortBy);
		//drpDownSortBy.click();
		WebElement webElement = driver.findElement(By.xpath(MessageFormat.format(sortBy, filters)));
		rm.bClickableElelemnt(webElement);
		webElement.click();
		rm.waitForPageLoad();
	}

	public HashMap<String, String> getFirstProductIDWhichHasPrice() {
		HashMap<String, String> mapProduct = new HashMap<>();
		String productPrice = "//div[@data-component-type=''s-search-result''][{0}]//span[@class=''a-price-whole'']";
		String productFraction = "//div[@data-component-type=''s-search-result''][{0}]//span[@class=''a-price-whole'']/following-sibling::span[@class=''a-price-fraction'']";
		int productID = 0;

		Double productLowestPrice = 0.00;
		String productTitleForLowestPrice = null;

		System.out.println("Finding product id " + titleXpath.size());

		for (int i = 0; i < titleXpath.size(); i++) {
			if (driver.findElements(By.xpath(MessageFormat.format(productPrice, i+1))).size() > 0) {
				
				System.out.println(driver.findElement(By.xpath(MessageFormat.format(productPrice, i+1))).getText() + " : "
						+ titleXpath.get(i).getText());
				String newProductPrice = driver.findElement(By.xpath(MessageFormat.format(productPrice, i+1))).getText();
				// System.out.println(newProductPrice);
				String newProductPriceFriction = driver.findElement(By.xpath(MessageFormat.format(productFraction, i+1)))
						.getText();
				// System.out.println(newProductPrice+"======"+newProductPriceFriction);
				Double price = Double.parseDouble(newProductPrice + "." + newProductPriceFriction);
				
				if (productLowestPrice == 0.00 || (productLowestPrice > 0.00 && price < productLowestPrice)) {
					
					productID = i;
					productLowestPrice = price;
					productTitleForLowestPrice = driver.findElement(By.xpath(MessageFormat.format(strPriceXpath, i+1)))
							.getText();
					System.out.println(productLowestPrice + " === " + productTitleForLowestPrice);
				}

			}
		}

		System.out.println("Product id " + productID + " " + Double.toString(productLowestPrice).trim() + " "
				+ productTitleForLowestPrice.trim());
		mapProduct.put("price", Double.toString(productLowestPrice).trim());
		mapProduct.put("title", productTitleForLowestPrice.trim());

		// clicking on lowest price product
		clickElementByJavaScript(driver.findElement(By.xpath(MessageFormat.format(productPrice, productID+1))));

		return mapProduct;
	}

	@FindBy(xpath = "//span[@id='productTitle']")
	public WebElement productDetailTitle;

	@FindBy(xpath = "//span[@id='priceblock_ourprice']")
	public WebElement productDetailPrice;

	public void onHoveElement(WebElement we) {
		Actions actions = new Actions(driver);
		actions.moveToElement(we).build().perform();
	}
}
