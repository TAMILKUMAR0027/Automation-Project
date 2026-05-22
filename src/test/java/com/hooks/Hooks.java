package com.hooks;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.driver.DriverClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger log =
            LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
    	DriverClass.initDriver();
        log.info("Browser launched successfully");
    }

    @After
    public void tearDown(Scenario scenario) {


        try {

            // CHECK DRIVER NULL
            if (scenario.isFailed()
                    && DriverClass.getDriver() != null) {

                // CREATE SCREENSHOTS FOLDER
                File screenshotFolder =
                        new File("screenshots");

                if (!screenshotFolder.exists()) {

        if (DriverClass.getDriver() == null) {
            log.error("Driver is NULL. Skipping screenshot and quit.");
            return;
        }

        if (scenario.isFailed()) {

            try {
                File screenshot = ((TakesScreenshot) DriverClass.getDriver())
                        .getScreenshotAs(OutputType.FILE);

                File destinationFile = new File(
                        "screenshots/" +
                                scenario.getName().replaceAll(" ", "_")
                                + ".png");

                destinationFile.getParentFile().mkdirs();


                    screenshotFolder.mkdirs();
                }

                byte[] screenshotBytes = ((TakesScreenshot) DriverClass.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");

                log.error("Scenario Failed : "
                        + scenario.getName());

            } catch (IOException e) {
                log.error("Screenshot capture failed : " + e.getMessage());
            }

        } else {
            log.info("Scenario Passed : " + scenario.getName());
        }

        DriverClass.quitDriver();
        log.info("Browser closed successfully");
    }
}