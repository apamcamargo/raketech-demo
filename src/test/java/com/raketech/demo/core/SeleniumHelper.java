package com.raketech.demo.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import com.raketech.demo.utils.AllureHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.raketech.demo.utils.LogWrapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumHelper {
    private final DriverFactory factory = new DriverFactory();
    private final AllureHelper allureHelper = new AllureHelper();
    public static final Logger log = new LogWrapper().getLog();
    private static final int TIMEOUT = 20;

    /**
     * Loads the specified URL in the browser.
     * Attaches a screenshot to Allure before and after the action.
     *
     * @param url The URL to load.
     */
    public void loadURL(String url) {
        log.info("Navigating to URL: {}", url);
        allureHelper.addMessage("Navigating to URL", url);
        factory.getDriver().get(url);
        allureHelper.addScreenshot(factory.getDriver(), "Page loaded: " + url);
    }

    /**
     * Finds and returns the WebElement identified by the locator.
     * If not found, throws an exception.
     *
     * @param locator The locator of the element.
     * @return The WebElement found.
     */
    public WebElement findElement(By locator) {
        try {
            return factory.getDriver().findElement(locator);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to find element: " + locator, e);
        }
    }

    /**
     * Waits until the element is clickable.
     *
     * @param locator The locator of the element to wait for.
     */
    public void waitElementClickable(By locator) {
        log.info("Waiting for element to be clickable: {}", locator);
        WebDriverWait wait = new WebDriverWait(factory.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new IllegalArgumentException("Element not clickable: " + locator, e);
        }
    }

    /**
     * Clicks on the element identified by the locator.
     * Attaches a screenshot to Allure after the click.
     *
     * @param locator The locator of the element to click.
     */
    public void click(By locator) {
        waitElementClickable(locator);
        log.info("Clicking on element: {}", locator);
        findElement(locator).click();
        allureHelper.addScreenshot(factory.getDriver(), "Clicked on element: " + locator);
    }

    /**
     * Scrolls the viewport to bring the specified element into view.
     *
     * @param locator The locator of the element to scroll to.
     */
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) factory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolled to element: {}", locator);
        allureHelper.addScreenshot(factory.getDriver(), "Scrolled to element: " + locator);
    }

    /**
     * Waits for the specified element to become visible.
     *
     * @param locator The locator of the element to wait for.
     */
    public void waitVisibilityElement(By locator) {
        log.info("Waiting for visibility of element: {}", locator);
        WebDriverWait wait = new WebDriverWait(factory.getDriver(), Duration.ofSeconds(TIMEOUT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new IllegalArgumentException("Element not visible: " + locator, e);
        }
    }

    /**
     * Sends the specified text to the element and clears the field before typing.
     * Attaches a screenshot to Allure after the action.
     *
     * @param locator The locator of the element.
     * @param text    The text to input.
     */
    public void sendKeys(By locator, String text) {
        waitVisibilityElement(locator);
        log.info("Sending keys to element: {}", locator);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
        allureHelper.addScreenshot(factory.getDriver(), "Sent keys to element: " + locator);
    }

    /**
     * Sends the specified text followed by an Enter key to the element.
     *
     * @param locator The locator of the element.
     * @param text    The text to input.
     */
    public void sendKeysWithEnter(By locator, String text) {
        waitVisibilityElement(locator);
        log.info("Sending keys with Enter to element: {}", locator);
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        allureHelper.addScreenshot(factory.getDriver(), "Sent keys with Enter to element: " + locator);
    }

    /**
     * Switches to a newly opened browser tab.
     *
     * @throws IllegalArgumentException If no new tab is detected.
     */
    public void switchToNewTab() {
        new WebDriverWait(factory.getDriver(), Duration.ofSeconds(3))
            .until(d -> factory.getDriver().getWindowHandles().size() > 1);

        ArrayList<String> tabs = new ArrayList<>(factory.getDriver().getWindowHandles());
        if (tabs.size() > 1) {
            factory.getDriver().switchTo().window(tabs.get(1));
            log.info("Switched to new tab.");
        } else {
            throw new IllegalArgumentException("No new tab to switch to.");
        }
    }

    /**
     * Retrieves the value of the specified attribute from an element.
     *
     * @param locator   The locator of the element.
     * @param attribute The name of the attribute to retrieve.
     * @return The value of the attribute.
     */
    public String getAttribute(By locator, String attribute) {
        WebElement element = findElement(locator);
        String value = element.getAttribute(attribute);
        log.info("Retrieved attribute [{}] from element [{}]: {}", attribute, locator, value);
        allureHelper.addMessage("Attribute Value", "Attribute: " + attribute + ", Value: " + value);
        return value;
    }
}
