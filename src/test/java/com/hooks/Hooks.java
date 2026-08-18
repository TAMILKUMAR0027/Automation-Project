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


// Before 

//
//private static final Logger log =
//        LogManager.getLogger(Hooks.class);
//
//@Before
//public void setUp() {
//
//    DriverClass.initDriver();
//
//    log.info("Browser launched successfully");
//}
//
//@After
//public void tearDown(Scenario scenario) {
//
//    try {
//
//        // CHECK DRIVER NULL
//        if (DriverClass.getDriver() == null) {
//
//            log.error("Driver is NULL. Skipping screenshot and quit.");
//
//            return;
//        }
//
//        // IF SCENARIO FAILED
//        if (scenario.isFailed()) {
//
//            // CREATE SCREENSHOTS FOLDER
//            File screenshotFolder =
//                    new File("screenshots");
//
//            if (!screenshotFolder.exists()) {
//
//                screenshotFolder.mkdirs();
//            }
//
//            // TAKE SCREENSHOT FILE
//            File screenshot =
//                    ((TakesScreenshot) DriverClass.getDriver())
//                            .getScreenshotAs(OutputType.FILE);
//
//            // DESTINATION PATH
//            File destinationFile =
//                    new File("screenshots/"
//                            + scenario.getName()
//                            .replaceAll(" ", "_")
//                            + ".png");
//
//            // COPY FILE
//            FileUtils.copyFile(screenshot, destinationFile);
//
//            // ATTACH SCREENSHOT TO REPORT
//            byte[] screenshotBytes =
//                    ((TakesScreenshot) DriverClass.getDriver())
//                            .getScreenshotAs(OutputType.BYTES);
//
//            scenario.attach(
//                    screenshotBytes,
//                    "image/png",
//                    "Failure Screenshot");
//
//            log.error("Scenario Failed : "
//                    + scenario.getName());
//
//        } else {
//
//            log.info("Scenario Passed : "
//                    + scenario.getName());
//        }
//
//    } catch (IOException e) {
//
//        log.error("Screenshot capture failed : "
//                + e.getMessage());
//    }
//
//    // CLOSE DRIVER
//    DriverClass.quitDriver();
//
//    log.info("Browser closed successfully");
//}


// After for @E2E
public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    // This will track if browser was opened for E2E (shared session)
    private static boolean e2eBrowserOpened = false;

    @Before
    public void setUp(Scenario scenario) {
        boolean isE2E = scenario.getSourceTagNames().contains("@E2E");

        if (isE2E) {
            // Open browser only once for all E2E scenarios
            if (!e2eBrowserOpened) {
                DriverClass.initDriver();
                e2eBrowserOpened = true;
                log.info(" E2E Browser Session Started (Shared across all E2E scenarios)");
            } else {
                log.info(" Reusing existing E2E browser session for: {}", scenario.getName());
            }
        }
        else {
            // Normal smoke/regression tests - fresh browser per scenario
            DriverClass.initDriver();
            log.info(" Browser launched for normal scenario: {}", scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        boolean isE2E = scenario.getSourceTagNames().contains("@E2E");

        try {
            if (DriverClass.getDriver() == null) {
                log.warn("Driver is null, skipping teardown for: {}", scenario.getName());
                return;
            }

            // Take screenshot on failure
            if (scenario.isFailed()) {
                captureScreenshot(scenario);
                log.error(" Scenario Failed: {}", scenario.getName());
            } else {
                log.info(" Scenario Passed: {}", scenario.getName());
            }

        } catch (Exception e) {
            log.error("Error during teardown: {}", e.getMessage());
        }

        // Close browser logic
        if (isE2E) {
            boolean isLastE2EScenario = scenario.getSourceTagNames().contains("@E2EEnd");

            if (isLastE2EScenario) {
                DriverClass.quitDriver();
                e2eBrowserOpened = false;
                log.info(" E2E Session Completed & Browser Closed");
            } else {
                log.info(" Keeping browser open for next E2E scenario");
            }
        }
        else {
            // Non-E2E scenarios always close browser
            DriverClass.quitDriver();
            log.info("Browser closed after normal scenario");
        }
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            File screenshotFolder = new File("screenshots");
            if (!screenshotFolder.exists()) {
                screenshotFolder.mkdirs();
            }

            File screenshot = ((TakesScreenshot) DriverClass.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".png";
            File destinationFile = new File("screenshots/" + fileName);

            FileUtils.copyFile(screenshot, destinationFile);

            // Attach to Cucumber report
            byte[] screenshotBytes = ((TakesScreenshot) DriverClass.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");

            log.info(" Screenshot saved: {}", fileName);

        } catch (IOException e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }
}