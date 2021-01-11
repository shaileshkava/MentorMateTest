package com.mentormanate.MentorMateTest.Runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.mentormanate.MentorMateTest.Utils.SetBrowserName;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/com/mentormanate/MentorMateTest/Features/ProductSelection.feature",
		glue="",
		plugin={"html:target/execution-report/execution-report.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml"})
public class Runner extends AbstractTestNGCucumberTests{
	SetBrowserName setBName = new SetBrowserName();
	
	@Parameters("browser")
	@BeforeClass
	public void setBrowser(String brorserName) {
		System.out.println("Setting browser from testng = "+brorserName);
		setBName.setBrowsername(brorserName);
	}
	
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		System.out.println("running scenarios");
		return super.scenarios();
	}
}
