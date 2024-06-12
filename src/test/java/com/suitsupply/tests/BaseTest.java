package com.suitsupply.tests;
import com.suitsupply.pages.CartPage;
import com.suitsupply.pages.CommonPage;
import com.suitsupply.pages.HomePage;
import com.suitsupply.pages.ProductsPage;
import com.testng.base.SuitSupplySpecificMethods;

public class BaseTest extends SuitSupplySpecificMethods {

	HomePage homePage = new HomePage(getWebDriver());
	CommonPage commonPage = new CommonPage(getWebDriver());
	CartPage cartPage = new CartPage(getWebDriver());
	ProductsPage sneakersPage = new ProductsPage(getWebDriver());
	
}
