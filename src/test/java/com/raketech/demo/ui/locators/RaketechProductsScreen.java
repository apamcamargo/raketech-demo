package com.raketech.demo.ui.locators;

import org.openqa.selenium.By;

public class RaketechProductsScreen {

	public By productByText(String productName) {
		return By.xpath("//h3[text()='"+ productName + "']/ancestor::div[5]");
	}
	
	public By productPageBtn(String productName) {
		return By.xpath("//h2[text()='"+ productName +"']/following-sibling::div//i[contains(@class, 'globe')]/parent::a");
	}
}
