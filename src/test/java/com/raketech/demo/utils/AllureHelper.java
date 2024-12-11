package com.raketech.demo.utils;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureHelper {

    /**
     * Capture a screenshot and add it as an attachment to the Allure report.
     *
     * @param driver share driver instance
     * @param title  the title of the screenshot in the Allure report
     */
    public void addScreenshot(WebDriver driver, String title) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(title, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            addMessage("Screenshot capture failed", e.getMessage());
        }
    }

    /**
     * Add a message as an attachment to the Allure report.
     *
     * @param title   the title of the attachment
     * @param message the message content to be added to the report
     */
    public void addMessage(String title, String message) {
        Allure.addAttachment(title, message);
    }
}
