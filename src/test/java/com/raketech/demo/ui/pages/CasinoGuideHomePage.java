package com.raketech.demo.ui.pages;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.raketech.demo.core.BaseTest;
import com.raketech.demo.ui.locators.CasinoGuideHomeScreen;
import io.qameta.allure.Step;

public class CasinoGuideHomePage extends BaseTest{
	
	CasinoGuideHomeScreen homeScreen = new CasinoGuideHomeScreen();

	@Step("Validating that the Casino Guide home page is fully loaded and displayed correctly")
	public void checkHomePageLogo() {
		web.switchToNewTab();
		web.waitVisibilityElement(homeScreen.casinoGuideHomeLogo);
	}

	@Step("Validating that the canonical href matches the expected URL: {url}")
	public void validateCanonicalHRef(String url) {
		assertEquals(url, web.getAttribute(homeScreen.canonicalHref, "href"));
	}
}
