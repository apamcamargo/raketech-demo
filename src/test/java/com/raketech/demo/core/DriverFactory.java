package com.raketech.demo.core;

import java.util.Collections;
import java.util.HashMap;

import com.raketech.demo.utils.YamlReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.raketech.demo.utils.LogWrapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver = null;
    public static final Logger log = new LogWrapper().getLog();

    /**
     * Creates and configures a new instance of a WebDriver based on the browser specified in the configuration.
     * Supports Chrome and Firefox browsers. Reads browser name from config.yml.
     *
     * @return A configured WebDriver instance.
     */
    private WebDriver createWebDriver() {
        String browser = new YamlReader().getValue("browser").toString().toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    /**
     * Provides the current WebDriver instance or creates a new one if none exists.
     *
     * @return The active WebDriver instance.
     */
    public WebDriver getDriver() {
        try {
            if (driver == null) {
                driver = createWebDriver();
                log.info("Driver successfully created!");
            }
        } catch (Exception e) {
            log.error("Error creating WebDriver: {}", e.getMessage());
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }

        return driver;
    }

    /**
     * Terminates the current WebDriver session and cleans up resources.
     * Logs the action and sets the driver instance to null.
     */
    public void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("Driver killed");
        }
    }

    /**
     * Closes the current browser window but keeps the WebDriver session active.
     * Logs the action for tracking purposes.
     */
    public void closeDriver() {
        if (driver != null) {
            driver.close();
            log.info("Driver window closed");
        }
    }

}
