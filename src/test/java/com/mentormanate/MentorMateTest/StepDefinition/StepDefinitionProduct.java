package com.mentormanate.MentorMateTest.StepDefinition;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.mentormanate.MentorMateTest.Manager.DriverServices;
import com.mentormanate.MentorMateTest.Pages.PageClassProduct;
import com.mentormanate.MentorMateTest.Utils.CustomExeption;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionProduct {
	
	private WebDriver driver;
	private DriverServices driverServices;
	private PageClassProduct pageClassProduct;
	private String title;
	private String price;
	
	//String url = "http://www.amazon.co.uk";
	
	public StepDefinitionProduct(DriverServices driverService) {
		this.driverServices = driverService;
		this.driver = driverServices.getDriver();
	}
	
	@Given("^Open URL$")
	public void open_url() throws Exception {
		driver.get(driverServices.getURL());
		pageClassProduct = new PageClassProduct(driver);
		if(pageClassProduct.bClickableElelemnt(pageClassProduct.acceptCoolie))
			pageClassProduct.acceptCoolie.click();
	}

	@When("^Click on hamburger menu$")
    public void click_on_hamburger_menu() throws Throwable {
        pageClassProduct.bVisibleElelemnt(pageClassProduct.hanburger);
        pageClassProduct.onHoveElement(pageClassProduct.hanburger);
        pageClassProduct.javaScriptClickElement(pageClassProduct.hanburger);
        pageClassProduct.waitForPageLoad();
        //pageClassProduct.bVisibleElelemnt(pageClassProduct.profile);
        
    }

	@And("^Select \"([^\"]*)\"$")
    public void select_something(String strMainCategory) throws Throwable {
		System.out.println("Select main catgory = "+strMainCategory);
		pageClassProduct.clickElementByName(pageClassProduct.mainCategory, strMainCategory);
	}
	
	@And("^Select Sub Caetgory \"([^\"]*)\"$")
    public void select_sub_category_something(String strSubCatrgory) throws Throwable {
		System.out.println("Select sub catgory = "+strSubCatrgory);
		pageClassProduct.clickElementByName(pageClassProduct.subCategory, strSubCatrgory);
		pageClassProduct.waitForPageLoad();
		//pageClassProduct.bInvisibleElement(pageClassProduct.hanburger);
	}
	
	@And("^Select Display Size \"([^\"]*)\"$")
    public void select_display_size_something(String strArg1) throws Throwable {
        System.out.println("Select display size = "+strArg1);
        pageClassProduct.filterByProduct("display",strArg1);
        pageClassProduct.waitForPageLoad();
    }
	
	@And("^Select CPU Type \"([^\"]*)\"$")
    public void select_cpu_type_something(String cputype) throws Throwable {
		System.out.println("Select CPU Type = "+cputype);
        pageClassProduct.filterByProduct("cpu",cputype);
        pageClassProduct.waitForPageLoad();
    }
	
	@And("^Select Storage Type \"([^\"]*)\"$")
    public void select_storage_type_something(String storagetype) throws Throwable {
		System.out.println("Select Storage size = "+storagetype);
        pageClassProduct.filterByProduct("storage",storagetype);
        pageClassProduct.waitForPageLoad();
		
    }
	
	@Then("^Select five starred laptop \"([^\"]*)\"$")
    public void select_five_starred_laptop_something(String starred) throws Throwable {
		System.out.println("Select 5 Start products = "+starred);
		pageClassProduct.filterByRatings(starred);
		pageClassProduct.waitForPageLoad();
	}
	
	@And("^Sort by price \"([^\"]*)\"$")
    public void sort_by_proce_something(String sortby) throws Throwable {
        pageClassProduct.sortByProducts(sortby);
        pageClassProduct.waitForPageLoad();
    }

	@And("^Select lowest price product$")
    public void select_lowest_price_product() throws Throwable {
        if(pageClassProduct.listProducts.size() <=0 )
        	throw new CustomExeption("No product has price");
        
        HashMap<String, String> mapProduct = pageClassProduct.getFirstProductIDWhichHasPrice();
        title = mapProduct.get("title").trim().replaceAll("[^a-zA-Z0-9]", " ");
        price = mapProduct.get("price");
        
        System.out.println(price +" === "+title);
    }
	
	@Then("^Verify Detail as Title and Price$")
    public void verify_detail_as_title_and_price() throws Throwable {
        String productDetailTitle = pageClassProduct.productDetailTitle.getText().trim().replaceAll("[^a-zA-Z0-9]", " ");
        String productDetailPrice = pageClassProduct.productDetailPrice.getText().replace("£", "").trim();
        
        if(!this.title.equals(productDetailTitle) || !this.price.equalsIgnoreCase(productDetailPrice)) {
        	System.out.println("Product title and price are not matching it should be "+this.title+" and "+this.price+" instead of "+productDetailTitle+" and "+
        productDetailPrice);
        	throw new CustomExeption("Product title and price are not matching it should be "+this.title+" and "+this.price+" instead of "+productDetailTitle+" and "+
        productDetailPrice);
        }else {
        	System.out.println("Product title and price are matching "+this.title+" and "+this.price+" instead of "+productDetailTitle+" and "+
        	        productDetailPrice);
        }
    }

}