package com.suitsupply.tests;
import com.selenium.base.SeleniumBaseImpl;
import com.suitsupply.pages.CartPage;
import com.suitsupply.pages.CommonPage;
import com.suitsupply.pages.HomePage;
import com.suitsupply.pages.JacketsPage;
import com.suitsupply.pages.SneakersPage;
import com.testng.base.SuitSupplySpecificMethods;

public class BaseTest extends SuitSupplySpecificMethods {

	HomePage homePage = new HomePage(getWebDriver());
	CommonPage commonPage = new CommonPage(getWebDriver());
	JacketsPage jacketsPage = new JacketsPage(getWebDriver());
	CartPage cartPage = new CartPage(getWebDriver());
	SneakersPage sneakersPage = new SneakersPage(getWebDriver());
	
}
