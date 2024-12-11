package com.raketech.demo.ui.locators;

import org.openqa.selenium.By;

public class RaketechHomeScreen {
	
    public By searchBarBtn = By.id("search-btn");
    public By searchBarTxt = By.xpath("//input[@placeholder='Search']");
    public By searchResultByText(String resultText) {
		return By.xpath("//div[@id='search-results']//a[text()='"+ resultText + "']");
	}

}
