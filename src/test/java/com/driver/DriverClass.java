package com.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverClass.class);

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {

        String browser = System.getProperty("browser", "chrome");
        logger.info("Initializing Browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }
        else {
            logger.error("Invalid Browser Name: " + browser);
            throw new RuntimeException("Invalid Browser Name: " + browser);
        }

        getDriver().manage().window().maximize();
        logger.info("Browser launched successfully");
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            logger.info("Closing browser session");
            getDriver().quit();
            driver.remove();
        }
    }
}