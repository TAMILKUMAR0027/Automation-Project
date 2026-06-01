package com.driver;

import com.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverClass {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final Logger logger = LogManager.getLogger(DriverClass.class);

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void initDriver() {

        try {

            String browser = ConfigReader.getProperties().getProperty("browser");

            String headlessValue = ConfigReader.getProperties().getProperty("headless");

            // ==========================================
            // DEFAULT VALUES
            // ==========================================

            if (browser == null || browser.trim().isEmpty()) {

                logger.warn("Browser value is null in properties file");
                logger.warn("Defaulting browser to CHROME");

                browser = "chrome";
            }

            boolean headless = headlessValue != null
                    && headlessValue.equalsIgnoreCase("true");

            logger.info("Initializing Browser : " + browser);

            logger.info("Headless Mode : " + headless);

            // ==========================================
            // CHROME DRIVER
            // ==========================================

            if (browser.equalsIgnoreCase("chrome")) {

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // Browser Stability
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");

                // Disable Chrome Popups
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-password-generation");

                // Disable Automation Banner
                options.setExperimentalOption(
                        "excludeSwitches",
                        new String[] { "enable-automation" });

                options.setExperimentalOption(
                        "useAutomationExtension",
                        false);

                // ==========================================
                // CHROME PREFERENCES
                // ==========================================

                Map<String, Object> prefs = new HashMap<>();

                prefs.put("credentials_enable_service", false);

                prefs.put("profile.password_manager_enabled", false);

                prefs.put("profile.password_manager_leak_detection", false);

                prefs.put(
                        "profile.default_content_setting_values.notifications",
                        2);

                options.setExperimentalOption("prefs", prefs);

                // ==========================================
                // HEADLESS MODE
                // ==========================================

                if (headless) {

                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");

                } else {

                    options.addArguments("--start-maximized");
                }

                driver.set(new ChromeDriver(options));

                logger.info("Chrome Browser Launched Successfully");
            }

            // ==========================================
            // FIREFOX DRIVER
            // ==========================================

            else if (browser.equalsIgnoreCase("firefox")) {

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();

                if (headless) {

                    options.addArguments("--headless");
                    options.addArguments("--width=1920");
                    options.addArguments("--height=1080");
                }

                driver.set(new FirefoxDriver(options));

                logger.info("Firefox Browser Launched Successfully");
            }

            // ==========================================
            // INVALID BROWSER
            // ==========================================

            else {

                logger.error("Invalid Browser Name : " + browser);

                throw new RuntimeException(
                        "Invalid Browser Name : " + browser);
            }

            // ==========================================
            // COMMON SETTINGS
            // ==========================================

            getDriver().manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            getDriver().manage().timeouts()
                    .pageLoadTimeout(Duration.ofSeconds(30));

            getDriver().manage().timeouts()
                    .scriptTimeout(Duration.ofSeconds(30));

            if (!headless) {

                getDriver().manage().window().maximize();
            }

            logger.info("Browser launched successfully");

        } catch (Exception e) {

            logger.error("Driver Initialization Failed");

            e.printStackTrace();

            throw e;
        }
    }

    public static void quitDriver() {

        try {

            if (getDriver() != null) {

                logger.info("Closing Browser Session");

                getDriver().quit();

                driver.remove();
            }

        } catch (Exception e) {

            logger.error("Error while closing browser");

            e.printStackTrace();
        }
    }
}