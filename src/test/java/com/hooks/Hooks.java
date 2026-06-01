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

    // Tracks whether the current run is an E2E suite
    // so the browser stays open across all E2E scenarios
    private static boolean isE2ESession = false;

    @Before
    public void setUp(Scenario scenario) {

        boolean isE2E = scenario.getSourceTagNames().contains("@E2E");

        if (isE2E) {

            // First E2E scenario — open the browser once
            if (DriverClass.getDriver() == null) {

                DriverClass.initDriver();
                isE2ESession = true;
                log.info("E2E session started — browser opened once for all E2E scenarios");

            } else {

                log.info("E2E reusing existing browser session for : {}", scenario.getName());
            }

        } else {

            // Non-E2E scenario — open a fresh browser as normal
            DriverClass.initDriver();
            isE2ESession = false;
            log.info("Browser launched successfully for : {}", scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        try {

            if (DriverClass.getDriver() == null) {
                log.error("Driver is NULL — skipping screenshot.");
                return;
            }

            if (scenario.isFailed()) {
                captureScreenshot(scenario);
                log.error("Scenario Failed : {}", scenario.getName());
            } else {
                log.info("Scenario Passed : {}", scenario.getName());
            }

        } catch (IOException e) {
            log.error("Screenshot capture failed : {}", e.getMessage());
        }

        boolean isE2E = scenario.getSourceTagNames().contains("@E2E");

        if (isE2E) {

            // Check if this is the LAST E2E scenario by tag @E2EEnd
            boolean isLastE2E = scenario.getSourceTagNames().contains("@E2EEnd");

            if (isLastE2E) {
                DriverClass.quitDriver();
                isE2ESession = false;
                log.info("E2E session ended — browser closed after final E2E scenario");
            } else {
                log.info("E2E keeping browser alive — scenario complete : {}", scenario.getName());
            }

        } else {

            // Non-E2E — always close after each scenario
            DriverClass.quitDriver();
            log.info("Browser closed successfully");
        }
    }

    private void captureScreenshot(Scenario scenario) throws IOException {

        try {
            File screenshotFolder = new File("screenshots");
            if (!screenshotFolder.exists()) {
                screenshotFolder.mkdirs();
            }

            File screenshot = ((TakesScreenshot) DriverClass.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File destinationFile = new File("screenshots/"
                    + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png");

            FileUtils.copyFile(screenshot, destinationFile);

            byte[] screenshotBytes = ((TakesScreenshot) DriverClass.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");

        } catch (Exception e) {
            log.error("Could not take screenshot : {}", e.getMessage());
        }
    }
}