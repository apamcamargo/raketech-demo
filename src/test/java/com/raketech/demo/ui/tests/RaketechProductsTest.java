package com.raketech.demo.ui.tests;

import com.raketech.demo.core.Hooks;
import com.raketech.demo.ui.pages.CasinoGuideHomePage;
import com.raketech.demo.ui.pages.RaketechHomePage;
import com.raketech.demo.ui.pages.RaketechProductsPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RaketechProductsTest extends Hooks{
	RaketechHomePage homePage = new RaketechHomePage();
	RaketechProductsPage productsPage = new RaketechProductsPage();
	CasinoGuideHomePage casinoGuideHomePage = new CasinoGuideHomePage();
	
	@Test
	@DisplayName("Validate CasinoGuide page load")
	@Description("Select Raketech Products, navigate to the CasinoGuide web page, and validate that it is fully loaded and displayed correctly")
	public void selectProductAndLoadPage() {
		homePage.navigateToRaketechPage();
		homePage.searchAndSelectPage("Products");
		productsPage.selectProduct("Casinoguide");
		productsPage.openProductWebPage("Casinoguide");
		casinoGuideHomePage.checkHomePageLogo();
		casinoGuideHomePage.validateCanonicalHRef("https://www.casinoguide.se/");
	}

}
