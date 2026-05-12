package com.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.stepDefinitions", "com.hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)

public class TestNgRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
