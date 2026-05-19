package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
<<<<<<< HEAD

        features = "src/test/resources/features/",
        glue = {"com.stepDefinitions", "com.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"
        },

        monochrome = true
)

=======
                features = "src/test/resources/features/",
                glue = {"com.stepDefinitions", "com.hooks"},
                plugin = {
                        "pretty",
                        "html:target/cucumber-report.html",
                        "json:target/cucumber-report.json",
                        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                        "rerun:target/failedrerun.txt"
                },tags="@AddReview",
                monochrome = true
        )
>>>>>>> 53412de3c319aa121ecfae50d0ee5fd43401e662

public class TestNgRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
