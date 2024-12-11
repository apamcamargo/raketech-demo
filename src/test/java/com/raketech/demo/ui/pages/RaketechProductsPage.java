package com.raketech.demo.ui.pages;

import com.raketech.demo.core.BaseTest;
import com.raketech.demo.ui.locators.RaketechProductsScreen;
import io.qameta.allure.Step;

public class RaketechProductsPage extends BaseTest {

    RaketechProductsScreen productsScreen = new RaketechProductsScreen();

	@Step("Select the {productName} in the Product page")
    public void selectProduct(String productName) {
        web.scrollToElement(productsScreen.productByText(productName));
        web.click(productsScreen.productByText(productName));
    }

	@Step("Open the {productName} webs page through the globe icon")
    public void openProductWebPage(String productName) {
        web.click(productsScreen.productPageBtn(productName));
    }

}
