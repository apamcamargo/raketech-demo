package com.raketech.demo.ui.pages;

import com.raketech.demo.core.BaseTest;
import com.raketech.demo.ui.locators.RaketechHomeScreen;
import io.qameta.allure.Step;

public class RaketechHomePage extends BaseTest{
	
	RaketechHomeScreen homeScreen = new RaketechHomeScreen();

	@Step("Navigate to Raketech page")
	public void navigateToRaketechPage() {
		web.loadURL(yamlReader.getValue("rkt_url").toString());
	}

	@Step("Search for {page} on search bar and open the {page} page")
	public void searchAndSelectPage(String page) {
		web.click(homeScreen.searchBarBtn);
		web.sendKeysWithEnter(homeScreen.searchBarTxt, page);
		web.click(homeScreen.searchResultByText(page));
	}
	

}
