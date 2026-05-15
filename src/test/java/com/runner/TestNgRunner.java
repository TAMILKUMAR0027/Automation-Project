package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features = "src/test/resources/features/",
		glue = {"com.stepDefinitions", "com.hooks"},
<<<<<<< HEAD
	    tags= "@Rest",
=======
	    tags= "@LoginFeature or @RegisterFeature",
>>>>>>> 5e60323c402a4dc4777cb964886688277681e4c5
		plugin = {
				"pretty",
				"html:target/cucumber-report.html",
				"json:target/cucumber-report.json",

				// Extent Report Adapter
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				// Allure Report Adapter
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		},
		
	
		
		monochrome = true
)

public class TestNgRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}